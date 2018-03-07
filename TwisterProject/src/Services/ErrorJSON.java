package Services;

import org.json.JSONException;
import org.json.JSONObject;

public class ErrorJSON {
	public static JSONObject serviceRefused(String message, int code_err) throws JSONException {
			JSONObject err = new JSONObject();
			
			err.put("le code de l'erreur", code_err);
			err.put("ErrorJson",message);
			
			return err;
			
	}
	
	public static JSONObject serviceAccepted(String message) throws JSONException{
			JSONObject acc= new JSONObject();
			acc.put(message, true);
			return acc;
	}
	

}
