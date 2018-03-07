package Services;


import java.sql.SQLException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import bd.BDException;
import bd.FriendTools;
import bd.UserTools;

public class FriendServices {
	public static JSONObject addFriend(String key, int id_friend) throws JSONException, SQLException, ClassNotFoundException{
		if(key==null ){
			return ErrorJSON.serviceRefused("argument manquant", -1);
		}
		boolean key_ok;
		try {
			key_ok = UserTools.checkKey(key);
			boolean id_friend_ok=UserTools.checkId(id_friend);
			if(!key_ok || !id_friend_ok) return ErrorJSON.serviceRefused("compte inexistant", 1);
			int mon_id= UserTools.getIdUser_key(key);
			FriendTools.addFriend(mon_id,id_friend);
			
		} catch (BDException e) {
			e.printStackTrace();
		}
		return ErrorJSON.serviceAccepted("Ami ajoute");
		
	}
	
		public static JSONObject RemoveFriend(String clef,int id_friend) throws BDException, JSONException{
		if(clef==null) return ErrorJSON.serviceRefused("arguments manquants",1);
		try{
			boolean exist1= UserTools.checkKey(clef);
			boolean exist2= UserTools.checkId(id_friend);
			if(!exist1 || !exist2) return ErrorJSON.serviceRefused("compte inexistant", 1);
			int myid=UserTools.getIdUser_key(clef);
			FriendTools.removeFriend(myid,id_friend);
			JSONObject json = new JSONObject();
			json.put("statu","ok");
			return 	json;
		}catch(Exception e){throw new BDException("...");
		}
	}
	
	public static JSONObject ListFriends(String clef) throws SQLException, BDException, JSONException{
		if(clef==null) return ErrorJSON.serviceRefused("arguments manquants",1);
		try{
			boolean exist1= UserTools.checkKey(clef);
			
			if(!exist1) return ErrorJSON.serviceRefused("compte inexistant", 1);
			int myid=UserTools.getIdUser_key(clef);
			JSONObject json = new JSONObject();
			List<Integer> l=FriendTools.listFriends(myid);
			for(int i=0;i<l.size();i++) json.put("id:", l.get(i));
			return 	json;
		}catch(Exception e){throw new BDException("...");
		}
	}

		
}



