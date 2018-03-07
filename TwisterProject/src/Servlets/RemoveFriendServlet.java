package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import Services.FriendServices;
import bd.BDException;

public class RemoveFriendServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse rep)throws ServletException, IOException {
		String clef=request.getParameter("clef");
		String idfriend=request.getParameter("idfriend");
		if(clef==null || idfriend==null){
			try {
				throw new ParameterException("Un parametre n'est pas affecte");
			} catch (ParameterException e) {				
				e.printStackTrace();
			}
		}
		rep.setContentType("text/plain");
		PrintWriter out = rep.getWriter ();
		try {
			out.println(FriendServices.RemoveFriend(clef,Integer.parseInt(idfriend)));
		} catch (BDException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
