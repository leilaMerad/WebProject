package bd;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;




public class CommentTools {
	
	private static List<String> l;

	public static boolean addComment(String key, String comment) throws UnknownHostException, BDException{
		try{
			Mongo m = new Mongo("localhost",27017);
			DB db = m.getDB("obj");
			DBCollection collection = db.getCollection("comments");
			BasicDBObject commentaire = new BasicDBObject();
			int mon_id = UserTools.getIdUser_key(key);
			int mon_login = UserTools.getLogin(mon_id);
			commentaire.put("author_login",mon_login);
			commentaire.put("commentaire:", comment);
			collection.insert(commentaire);
			return true;
			
		} catch (Exception e){
			throw new BDException("probleme dans l'ajout d'un commentaire");
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static List<String> searchMessage(String key, String mots, int friend_id) throws UnknownHostException, BDException{
		try{
			List<String> list = new ArrayList<String>();
			Mongo m = new Mongo("localhost",27017);
			DB db = m.getDB("obj");
			DBCollection collection = db.getCollection("comments");
			BasicDBObject commentaire = new BasicDBObject();
			int mon_id = UserTools.getIdUser_key(key);
			int mon_login = UserTools.getLogin(mon_id);
			int friend_log = UserTools.getLogin(friend_id);
			commentaire.put("author_login", mon_login);
			commentaire.put("friend_login", friend_log);
			DBCursor cursor = collection.find(commentaire);
			while(cursor.hasNext()){
				list.addAll((Collection<? extends String>) cursor.next());
			}
			return list;
			
		}catch(Exception e){
			throw new BDException("probleme dans la recherche d'un commentaire");
		}
		
		
	}
	
	@SuppressWarnings("unchecked")
	public static List<String> ListMessage(String userkey, String query, String contact) throws SQLException, BDException, UnknownHostException, ClassNotFoundException {
		l = new ArrayList<String>();
		l=null;
		if(userkey ==null && query==null && contact ==null) return l;
		if(userkey!=null && query==null && contact!=null) {
			Mongo m=new Mongo("li328.lip6.fr",27130);
			DB db=m.getDB("gr2_2017_debrito_yang"); 
			DBCollection collection =db.getCollection("Messages");
			BasicDBObject obj=new BasicDBObject();
			int id=UserTools.getIdUser_key(query);
			obj.put("author_id",id);
			obj.put("friend_login",contact);
			DBCursor cursor=collection.find(obj);
			while(cursor.hasNext()){
				l.addAll( (Collection<?extends String>) cursor.next());
			}
			return l;
		}
		if(userkey!=null && query!=null && contact==null){
			Mongo m=new Mongo("li328.lip6.fr",27130);
			DB db=m.getDB("gr2_2017_debrito_yang"); 
			DBCollection collection =db.getCollection("Messages");
			BasicDBObject obj=new BasicDBObject();
			int id=UserTools.getIdUser_key(query);
			obj.put("author_id",id);
			obj.put("text",query);
			DBCursor cursor=collection.find(obj);
			while(cursor.hasNext()){
				l.addAll( (Collection<?extends String>) cursor.next());
			}
			return l;
		}
		return l;
	}

	public static List<String> Search(String key, String query, String friend) {
		// TODO Auto-generated method stub
		return null;
	}

}
	
	


