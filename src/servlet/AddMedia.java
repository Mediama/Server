package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.Tools;

import storage.DatabaseManager;
import entity.Media;

/**
 * Servlet implementation class AddMedia
 */
@WebServlet("/AddMedia")
public class AddMedia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMedia() {
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
		
		String title=request.getParameter("title");
		String author=request.getParameter("author");
		String type=request.getParameter("type");
		
		Media.Type mediaType=null;
		
		try {
			if(title==null){
				ServletResult.sendResult(response, ServletResult.MISSING_TITLE);
				return;
			}
			else if(author==null){
				ServletResult.sendResult(response, ServletResult.MISSING_AUTHOR);
				return;
			}
			else{
				switch (type) {
				case "document":
					mediaType=Media.Type.DOC;
					break;
				case "movie":
					mediaType=Media.Type.MOVIE;
					break;
				case "sound":
					mediaType=Media.Type.SOUND;
					break;
				default:
					ServletResult.sendResult(response, ServletResult.MISSING_MEDIA_TYPE);
					return;
				}
			}
			
			Media media=new Media();
			media.setTitle(title);
			media.setAuthor(author);
			media.setType(mediaType);
			
			if(manager.getMediaDao().create(media)!=1){
				ServletResult.sendResult(response, ServletResult.ERROR);
			}
			
			String idMatter=request.getParameter("list_matter_id");
			if(idMatter!=null){
				try{
					for(int id : Tools.toIntList(idMatter, ";")){				
						manager.addMediaToMatter(media.getId(), id);
					}
				} catch (NumberFormatException e ){
					ServletResult.sendResult(response, ServletResult.BAD_INT_FORMAT);
					return;
				}
			}
			
			response.setStatus(HttpServletResponse.SC_CREATED);
			ServletResult.sendResult(response, ServletResult.SUCCESS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
