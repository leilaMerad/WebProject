package test;

import java.sql.Connection;
import java.sql.SQLException;

import org.json.JSONException;

import bd.BDException;
import bd.Database;
import Services.UserServices;

public class Test {

	public static void main(String[] args) throws JSONException, BDException, SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		
		String nom = "leila";
		String login = "3521587";
		String password = "root";
		String prenom = "leila";
		Class.forName("com.mysql.jdbc.Driver");
		Connection sql= Database.getMySQLConnection();
		
		UserServices.createUser(nom, prenom, login, password);

		UserServices.login(login, password);
	


	
	}

}