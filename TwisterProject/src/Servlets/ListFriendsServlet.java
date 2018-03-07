package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import bd.BDException;
import Services.FriendServices;

public class ListFriendsServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse rep)throws ServletException, IOException {
		String user=request.getParameter("user");
		if(user==null){
			try {
				throw new ParameterException("Un parametre n'est pas affecte");
			} catch (ParameterException e) {	
				e.printStackTrace();
			}
		}
		rep.setContentType("text/plain");
		PrintWriter out = rep.getWriter ();
		try {
			out.println(FriendServices.ListFriends(user));
		} catch (BDException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
