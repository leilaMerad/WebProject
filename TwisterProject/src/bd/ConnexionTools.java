package bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnexionTools {
	public static boolean isConnexion(String key) throws ClassNotFoundException, BDException{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn= Database.getMySQLConnection();
			
			String query= "SELECT cle FROM CONNEXIONS where cle = '" +key+"'";
			Statement instruction = conn.createStatement();
			ResultSet retour= instruction.executeQuery(query);
			if(retour.next()==true){
				retour.close();
				instruction.close();
				conn.close();
				return true;
			}
			retour.close();
			instruction.close();
			conn.close();
			return false;
		}catch( SQLException e){
				throw new BDException("erreur dans la creation");
			
		}
	}
	
	public static boolean removeConnexion(String key){
		return false;
		
	}
}

