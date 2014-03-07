package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import storage.DatabaseManager;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import entity.Media;
import entity.Module;
import entity.User;

/**
 * Servlet implementation class GetAvailableMedia
 */
@WebServlet("/GetAvailableMedia")
public class GetAvailableMedia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAvailableMedia() {
        super();
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
		DatabaseManager manager=DatabaseManager.getManager();
		ObjectMapper oMap=new ObjectMapper();
		
		String idUser=request.getParameter("user_id");
		
		try{
			int id;
			
			if(idUser!=null){
				id=Integer.parseInt(idUser);
				
				User user=manager.getUserDao().queryForId(id);
				if(user==null){
					ServletResult.sendResult(response, ServletResult.NOT_FOUND);
					return;
				}
				
				if(user.getModule()!=null){
					List<Module> list=manager.getDescendingModule(user.getModule().getId());
					List<ModuleResult> listResult=new ArrayList<ModuleResult>();
					
					for(Module module : list){
						listResult.add(new ModuleResult(module, manager.getMediaFromModule(module.getId())));
					}
					
					response.getWriter().append(
							oMap.writeValueAsString(new Result(
									ServletResult.SUCCESS, listResult)));
				}
				else{
					response.getWriter().append(
							oMap.writeValueAsString(new Result(
									ServletResult.SUCCESS, new ArrayList<ModuleResult>())));
				}
				
			}
			else{
				ServletResult.sendResult(response, ServletResult.MISSING_USER_ID);
			}
		} catch (NumberFormatException e ){
			ServletResult.sendResult(response, ServletResult.BAD_INT_FORMAT);
			e.printStackTrace();
		} catch (SQLException e) {
			ServletResult.sendResult(response, ServletResult.ERROR);
			e.printStackTrace();
		}
	}
	
	
	private class Result extends ServletResult{
		@JsonProperty("list")
		public Collection<ModuleResult> list;

		public Result(int result, Collection<ModuleResult> list) {
			super(result);
			
			this.list=list;
		}
		
	}
	
	private class ModuleResult{
		@JsonProperty("id")
		public int id;
		@JsonProperty("order")
		public int order;
		@JsonProperty("media")
		public Collection<Media> media;
		
		public ModuleResult(Module module, Collection<Media> media){
			id=module.getId();
			order=module.getOrder();
			this.media=media;
		}
	}

}
