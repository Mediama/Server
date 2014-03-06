package servlet;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import storage.DatabaseManager;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import entity.Media;

/**
 * Servlet implementation class GetAllMedia
 */
@WebServlet("/GetAllMedia")
public class GetAllMedia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllMedia() {
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
		
		String idMatter=request.getParameter("matter_id");
		String idModule=request.getParameter("module_id");
		String idFormation=request.getParameter("formation_id");
		
		try{
			int id;
			
			if(idMatter!=null){
				id=Integer.parseInt(idMatter);
				
				response.getWriter().append(
						oMap.writeValueAsString(new Result(
								ServletResult.SUCCESS, manager.getMediaFromMatter(id))));
			}
			else if(idModule!=null){
				id=Integer.parseInt(idMatter);
				
				response.getWriter().append(
						oMap.writeValueAsString(new Result(
								ServletResult.SUCCESS, manager.getMediaFromModule(id))));
			}
			else if(idFormation!=null){
				id=Integer.parseInt(idMatter);
				
				response.getWriter().append(
						oMap.writeValueAsString(new Result(
								ServletResult.SUCCESS, manager.getMediaFromFormation(id))));
			}
			else{
				response.getWriter().append(
						oMap.writeValueAsString(new Result(
								ServletResult.SUCCESS, manager.getAllMedia())));
			}
			
		} catch (NumberFormatException e ){
			ServletResult.sendResult(response, ServletResult.BAD_INT_FORMAT);
		}
	}
	
	
	private class Result extends ServletResult{
		@JsonProperty("list")
		public Collection<Media> list;

		public Result(int result, Collection<Media> list) {
			super(result);
			
			this.list=list;
		}
		
	}
}
