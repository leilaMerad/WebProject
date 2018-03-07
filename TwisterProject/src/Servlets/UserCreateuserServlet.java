package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import bd.BDException;
import Services.UserServices;

public class UserCreateuserServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String login = request.getParameter("login");
		String password= request.getParameter("password");
		String nom= request.getParameter("nom");
		String prenom=request.getParameter("prenom");
		
		if(login==null || password==null || nom == null || prenom == null){
				try {
					throw new ParameterException("probleme de parametre");
				} catch (ParameterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter(); 
		
		try {
			Class.forName("com.sql.jdbc.Driver");
			out.println(UserServices.createUser(login, password, nom, prenom));
		} catch (JSONException | BDException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
