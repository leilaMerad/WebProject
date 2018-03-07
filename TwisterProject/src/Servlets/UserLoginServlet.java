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
import Services.UserServices;

public class UserLoginServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		String login= request.getParameter("login");
		String password= request.getParameter("password");
		try{
			if(login==null || password==null){
				throw new ParameterException("il manque un parametre");
			}
		} catch (ParameterException e){}
		
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			out.println(UserServices.login(login, password).toString());
		} catch(JSONException e){
		} catch (BDException e) {
			// TODO Auto-generated catch block
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
