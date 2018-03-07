package Services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONException;
import org.json.JSONObject;

import bd.BDException;
import bd.ConnexionTools;
import bd.Database;
import bd.UserTools;

public class UserServices {
	public static JSONObject login ( String login, String password) throws JSONException, BDException, SQLException, ClassNotFoundException{
			if(login == null || password == null){
					return ErrorJSON.serviceRefused("wrong argument",-1);
			}
			
			try {
					
					Connection c = Database.getMySQLConnection();
					Statement lecture = c.createStatement();
					
					boolean is_user = UserTools.userExists(login);
					if(is_user==false){
						return ErrorJSON.serviceRefused("unknown user"+login,1);
					}
					
					boolean password_ok= UserTools.checkPassword(login,password);
					
					if(!password_ok){
						return ErrorJSON.serviceRefused("bad password"+login,2);
					}
					int id_user=UserTools.getIdUser(login);
					System.out.println(id_user);
					JSONObject retour = new JSONObject();
					String key= UserTools.insertSession(id_user,0);
					if(key != null && ConnexionTools.isConnexion(key)){
						return ErrorJSON.serviceAccepted("utilisateur connecte");
					}
					else{
							return ErrorJSON.serviceRefused("Erreur cle ou connexion", 0);
					}
			} catch ( BDException e){
					return ErrorJSON.serviceRefused("accés refusé", 1000);
			} catch (JSONException f){
				return ErrorJSON.serviceRefused("erreur", 100);
			}
	}

	public static JSONObject createUser(String login, String password, String nom, String prenom) throws JSONException, BDException, SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
			if(login == null || password == null || nom == null || prenom == null){
					return ErrorJSON.serviceRefused("wrong argument", -1);
			}
			
			try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection c = Database.getMySQLConnection();
					Statement lecture = c.createStatement();
				
					boolean is_user = UserTools.userExists(login);
			
					if(is_user){
						return ErrorJSON.serviceRefused("Login exist", 3);
					}
					
					UserTools.insertUser(nom,prenom,login,password);
					return ErrorJSON.serviceAccepted("Utilisateur ajoute");
			} catch ( BDException e) {
				return ErrorJSON.serviceRefused("problem SQL", 1000);
			}	
					
	}
	
	public static JSONObject logout(String key) throws JSONException, SQLException, BDException, ClassNotFoundException{
			if (key==null){
				return ErrorJSON.serviceRefused("wrong argument", -1);
			}
			if( !ConnexionTools.isConnexion(key)){
					return ErrorJSON.serviceRefused("unknown Connexion", 1);
			}
			boolean b=ConnexionTools.removeConnexion(key);
			if(b==false) return ErrorJSON.serviceRefused("erreur de deconnexion",10);
			return ErrorJSON.serviceAccepted("deconnexion");	
	}
}




