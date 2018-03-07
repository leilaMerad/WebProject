package bd;

import java.io.BufferedReader;
import java.io.File;
import java.sql.*;
import java.util.UUID;
import java.sql.DriverManager;

public class UserTools {
	
	public static boolean userExists(String login) throws BDException, ClassNotFoundException{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn= Database.getMySQLConnection();
			
			String query= "SELECT id_user FROM USER where login = '" +login+"'";
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
	
	public static int getIdUser( String login) throws BDException, ClassNotFoundException, SQLException{
		int id_user=-1;
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = Database.getMySQLConnection();
		String query= "SELECT id_user FROM USER where login = ' " + login + " ' ";
		Statement instruction;
		try {
			instruction = c.createStatement();
			ResultSet rs=instruction.executeQuery(query);
			//ResultSet rs= instruction.getResultSet();
			if(rs.next()){
				id_user=rs.getInt(1);
				System.out.println(id_user);
			}
			rs.close();
			instruction.close();
			c.close();
			return id_user;
		} catch (SQLException e) {
			throw new BDException("probleme dans la récuperation de l'id de l'USER:"+login);
		}

	}
	
	public static String insertSession(int id, int  admin) throws SQLException{
		String key=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection c = Database.getMySQLConnection();
			key = generateRandomKey();
		
			String query = "INSERT INTO CONNEXIONS Values('"+key+"','"+id+"',NOW(),'"+admin+"')";
			
			Statement st = c.createStatement();
			st.executeUpdate(query);
			System.out.println(key);
			st.close();
			c.close();
		}catch(SQLException e){
			throw new SQLException("Erreur"+e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return key;
	}
	
	private static String generateRandomKey(){
		String a= UUID.randomUUID().toString();
		String b="";
	
		 for(int i=0;i<30;i++) {
			
			 b+=a.charAt(i);
			 
		 }

		  return b;
	}
	
	public static boolean checkPassword(String login, String password) throws BDException, SQLException, ClassNotFoundException{
		boolean mdp;
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = Database.getMySQLConnection();
		String query= "SELECT id_user FROM USER where login = ' " + login + " ' and mdp = '"+password+"'";
		try{
			Statement instruction = c.createStatement();
			instruction.executeQuery(query);
			ResultSet retour= instruction.getResultSet();
			System.out.println(retour.next());
			if(retour.next()==true){
				retour.close();
				instruction.close();
				c.close();
				return true;
			}
			retour.close();
			instruction.close();
			c.close();
			return true;
		}catch( SQLException e){
			throw new BDException("Erreur dans la verification du mdp de l'USER"+login);
		
		}	
		
	}
	
	public static void insertUser(String login, String password, String nom, String prenom) throws BDException, SQLException, ClassNotFoundException{
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn= Database.getMySQLConnection();
		String update = "INSERT INTO USER Values(NULL, '" +nom+ "', '"+prenom+"' ,'" +login+ "' ,'" +password+"')";
		
		
		Statement instruction = conn.createStatement();
		
		int i = instruction.executeUpdate(update);
		if(i==1){
			System.out.println("user creer");
		}
		instruction.close();
		conn.close();

	}

	public static boolean checkKey(String key) throws BDException, SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {

		boolean bool=false;
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn= Database.getMySQLConnection();
		String query="SELECT cle FROM Connexions where `cle=`" +key;
		Statement instruction;
		try {
			instruction = conn.createStatement();
			instruction.executeQuery(query);
			ResultSet rs = instruction.getResultSet();
			if(rs.next()) bool=true;
			instruction.close();
			conn.close();
			return bool;
		} catch (SQLException e) {
			throw new BDException("probleme dans l'insertion d'un USER");
		}
	}

	public static boolean checkId(int id) throws BDException, SQLException, ClassNotFoundException {

		boolean bool=false;
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn= Database.getMySQLConnection();
		String query="SELECT id FROM USER where `id=`" +id;
		Statement instruction;
		try {
			instruction = conn.createStatement();
			instruction.executeQuery(query);
			ResultSet rs = instruction.getResultSet();
			if(rs.next()) bool=true;
			instruction.close();
			conn.close();
			return bool;
		} catch (SQLException e) {
			throw new BDException("probleme dans l'insertion d'un USER");
		}
	}
	
	public static void removeSession(String key) throws BDException, SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn= Database.getMySQLConnection();
		String query="DELETE FROM Connexions where `cle=`" +key;
		Statement instruction;
		try {
			instruction = conn.createStatement();
			instruction.executeQuery(query);
			instruction.close();
			conn.close();
		}catch(SQLException e) {
			throw new BDException("probleme dans l'insertion d'un USER");
		}
	}

	public static int getIdUser_key(String key) throws BDException, SQLException, ClassNotFoundException {

		int id_user=-1;
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn= Database.getMySQLConnection();
		String query="SELECT id FROM Connexions where `key=`" +key;
		Statement instruction;
		try {
			instruction = conn.createStatement();
			instruction.executeQuery(query);
			ResultSet rs= instruction.getResultSet();
			if(rs.next()){
				id_user=rs.getInt(1);
			}
			rs.close();
			instruction.close();
			conn.close();
			return id_user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new BDException("probleme dans la récuperation de l'id de l'USER:"+key);
		}
	}

	public static int getLogin(int mon_id) throws BDException, SQLException, ClassNotFoundException {
		int login_user=-1;
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn= Database.getMySQLConnection();
		String query="SELECT login FROM Connexions where `id=`" +mon_id;
		Statement instruction;
		try {
			instruction = conn.createStatement();
			instruction.executeQuery(query);
			ResultSet rs= instruction.getResultSet();
			if(rs.next()){
				login_user=rs.getInt(1);
			}
			rs.close();
			instruction.close();
			conn.close();
			return login_user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new BDException("probleme dans la récuperation du login de l'USER:"+mon_id);
		}
	}

}

