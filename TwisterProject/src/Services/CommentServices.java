package Services;


import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import bd.BDException;
import bd.CommentTools;
import bd.UserTools;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class CommentServices {
	public static JSONObject AddComment(String key,String text) throws JSONException, UnknownHostException, SQLException, BDException, ClassNotFoundException{ //login=author_login
		if(key==null || text==null ) return ErrorJSON.serviceRefused("arguments manquants",1);
		boolean exist= UserTools.checkKey(key);
		if(!exist) return ErrorJSON.serviceRefused("pseudo deja utilisé", 1);
		try {
			if(CommentTools.addComment(key,text)){
				JSONObject json = new JSONObject();
			
				json.put("statu","ok");
				return json;
			}else{
				return ErrorJSON.serviceRefused("arguments manquants",1);
			}	
			} catch (JSONException e) {
				return ErrorJSON.serviceRefused("arguments manquants",1);
			}		
	}
	
	public static JSONObject Search(String key, String query, String friend) throws SQLException, BDException, UnknownHostException, JSONException, ClassNotFoundException {
		if(key==null) return ErrorJSON.serviceRefused("arguments manquants",1);
		boolean exist= UserTools.userExists(key);
		if(!exist) return ErrorJSON.serviceRefused("pseudo deja utilisé", 1);
		boolean exist2;
		if(friend!=null){
			exist2= UserTools.userExists(friend);
			if(!exist2) return ErrorJSON.serviceRefused("pseudo deja utilisé", 1);
		}
		
		List l=CommentTools.Search(key,query,friend);
		if(l==null) return ErrorJSON.serviceRefused("pas de message",1);
		int i=0;
		JSONObject jsn=new JSONObject();
		while(i++<l.size()) jsn.put("Messages:", l.get(i));
		return jsn;
		
	}
	
	public static JSONObject  ListMessages(String userkey, String query, String contact) throws SQLException, BDException, UnknownHostException, JSONException, ClassNotFoundException {
		int b=UserTools.getIdUser_key(userkey);
		if(b==-1) return ErrorJSON.serviceRefused(userkey+" n'existe pas",1);
		List l=CommentTools.ListMessage(userkey, query, contact);
		JSONObject jbs=new JSONObject();
		if(l==null) jbs=null;
		else{
			int i=0;
			while(l.get(i++)!=null) {
				System.out.print(l.get(i));
				jbs.put("Message:",l.get(i));
			}
		}
		return jbs;
		
	}
	
	

}
