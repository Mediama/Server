package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;

import storage.DatabaseManager;
import entity.User;

/**
 * Servlet implementation class Subscribe
 */
@WebServlet("/Subscribe")
public class Subscribe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Subscribe() {
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
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response){
		DatabaseManager manager=DatabaseManager.getManager();
		
		String login=request.getParameter("login");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		
		try {
			if(login==null){
				ServletResult.sendResult(response, ServletResult.MISSING_EMAIL);
				return;
			}
			else if(password==null){
				ServletResult.sendResult(response, ServletResult.MISSING_PASSWORD);
				return;
			}
			else if(email==null){
				ServletResult.sendResult(response, ServletResult.MISSING_EMAIL);
				return;
			}
			
			User user=new User();
			user.setLogin(login);
			user.setEmail(email);
			user.setPassword(password);
			
			if(manager.getUserDao().create(user)==1){
				ServletResult.sendResult(response, ServletResult.SUCCESS);
				response.setStatus(HttpServletResponse.SC_CREATED);
				return;
			}
			
			ServletResult.sendResult(response, ServletResult.ERROR);
		} catch (SQLException | JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
