package collin.wearhacks.networknow;
import java.io.*;
import java.net.*;

// JSON SHIT
import org.json.*;

public class HTTPRequests{

  /*
   * Get JSON Data and store it in a string called result
   */
	public String getJSON(String urlToRead) {
		URL url;
		HttpURLConnection conn;
		BufferedReader rd;
		String line;
		String result = "";
		try {
			url = new URL(urlToRead);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((line = rd.readLine()) != null) {
				result += line;
			}
			rd.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

  /*
   * Takes a JSONString and creates a JSONArray
   */
	public String[] JSON2Array(String string) throws JSONException{
		JSONArray jObj = new JSONArray(string);
		final String[] array_spinner = new String[jObj.length()];
		try{
      // Create new JSONArray
			
			
      for(int i=0; i<jObj.length(); i++){
				JSONObject json_data = jObj.getJSONObject(i);

        // Header's we can grab
        // we can add more to this
				String role = json_data.getString("role");
				String uname = json_data.getString("username");
				
        array_spinner[i] = role+" "+uname;
			}
			
      for(int i=0; i<jObj.length(); i++){
				System.out.println(array_spinner[i]);
				System.out.println("\n");
			}
      
		}catch(JSONException e){
			e.printStackTrace();
			Log.w("ERROR", "No Data found" );
		}
		return array_spinner;
	}

	public static void main(String args[]) throws JSONException{
		HTTPRequests c = new HTTPRequests();
		String userMatches = "http://104.131.105.6:3000/matches/weeks/hotspot/b9407f30-f5f8-466e-aff9-25556b57fe6d5555522222";
		c.JSON2Array(c.getJSON(userMatches));
	}
}
