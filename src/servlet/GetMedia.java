package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import storage.DatabaseManager;

import com.fasterxml.jackson.databind.ObjectMapper;

import entity.Media;

/**
 * Servlet implementation class GetMedia
 */
@WebServlet("/GetMedia")
public class GetMedia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMedia() {
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
		
		String idValue=request.getParameter("id");
		if(idValue==null){
			ServletResult.sendResult(response, ServletResult.MISSING_ID);
			return;
		}
		
		System.out.println(request.getQueryString());
		
		int id;
		try{
			id=Integer.parseInt(idValue);			
		} catch (Exception e) {
			ServletResult.sendResult(response, ServletResult.BAD_INT_FORMAT);
			return;
		}
		
		try {
			Media media=manager.getMediaDao().queryForId(id);
			
			if(media==null){
				ServletResult.sendResult(response, ServletResult.NOT_FOUND);
				return;
			}
			else{
				response.getWriter().append(
						oMap.writeValueAsString(media));
				
				response.getWriter().close();		
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			ServletResult.sendResult(response, ServletResult.ERROR);
		}
		
	}

}
