package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.Tools;

import storage.DatabaseManager;

/**
 * Servlet implementation class AssMediaWithMatter
 */
@WebServlet("/AssMediaWithMatter")
public class AssMediaWithMatter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssMediaWithMatter() {
        super();
        // TODO Auto-generated constructor stub
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
		
		String idMedia=request.getParameter("media_id");
		String idMatter=request.getParameter("list_matter_id");
		
		try{
			int mediaId;			
			if(idMedia!=null){
				mediaId=Integer.parseInt(idMatter);
			}
			else{
				ServletResult.sendResult(response, ServletResult.MISSING_ID);
				return;
			}
			
			if(idMatter!=null){
				try{
					for(int id : Tools.toIntList(idMatter, ";")){				
						manager.addMediaToMatter(mediaId, id);
					}
				} catch (NumberFormatException e ){
					ServletResult.sendResult(response, ServletResult.BAD_INT_FORMAT);
				}
				
				response.setStatus(HttpServletResponse.SC_CREATED);
				ServletResult.sendResult(response, ServletResult.SUCCESS);
			}
			else{
				ServletResult.sendResult(response, ServletResult.MISSING_ID);
			}
		} catch (NumberFormatException e ){
			ServletResult.sendResult(response, ServletResult.BAD_INT_FORMAT);
		}
	}
}
