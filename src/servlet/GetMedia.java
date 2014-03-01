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
		sendResult(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sendResult(request, response);
	}
	
	private void sendResult(HttpServletRequest request, HttpServletResponse response) throws IOException{
		DatabaseManager manager=DatabaseManager.getManager();
		ObjectMapper oMap=new ObjectMapper();
		
		int id=Integer.parseInt(request.getParameter("id"));
		
		try {
			response.getWriter().append(
					oMap.writeValueAsString(manager.getMediaDao().queryForId(id)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.getWriter().close();
	}

}
