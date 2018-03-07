package bd;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class FriendTools {

	public static void addFriend(int mon_id, int id_friend) throws BDException, SQLException, ClassNotFoundException {
		Connection conn= Database.getMySQLConnection();
		String query="INSERT INTO Friends (from_id,to_id) Values(mon_id,id_friend)";
		Statement instruction;
		try {
			instruction = conn.createStatement();
			instruction.executeQuery(query);
			instruction.close();
			conn.close();
		} catch (Exception e) {
			throw new BDException("probleme au niveau de l'ajout d'ami");
		}
	}
	
	public static void removeFriend(int mon_id, int id_friend) throws BDException, SQLException, ClassNotFoundException{
		Connection conn= Database.getMySQLConnection();
		String query="DELETE INTO Friends where `from_id=`" +mon_id+ " and `to_id=`" +id_friend;
		Statement instruction;
		try {
			instruction = conn.createStatement();
			instruction.executeQuery(query);
			instruction.close();
			conn.close();
		} catch (Exception e) {
			throw new BDException("probleme au niveau dans la suppression d'ami");
		}
	}
	
	public static List<Integer> listFriends(int mon_id) throws BDException, SQLException, ClassNotFoundException{
		List<Integer> list=new ArrayList<Integer>();
		Connection conn= Database.getMySQLConnection();
		String query="SELECT `to_id` INTO Friends where `from_id=`" +mon_id;
		Statement instruction;
		try {
			instruction = conn.createStatement();
			instruction.executeQuery(query);
			ResultSet rs = instruction.getResultSet();
			while(rs.next()){
				list.add(rs.getInt(1));
			}
			rs.close();
			instruction.close();
			conn.close();
			return list;
		} catch (Exception e) {
			throw new BDException("probleme de l'affichage de la liste d'ami");
		}
	}
		
			
}




