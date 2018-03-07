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

public class UserLogoutServlet extends HttpServlet {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String key=request.getParameter("key");
		try{
			if(key==null){
				throw new ParameterException("probleme de parametre");
			}
		}catch (ParameterException e){}
		
		response.setContentType("text/plain");
		PrintWriter out= response.getWriter();
		try{
			Class.forName("com.sql.jdbc.Driver");
			out.println(UserServices.logout(key));
		}catch(JSONException e){
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
