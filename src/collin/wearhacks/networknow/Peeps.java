package collin.wearhacks.networknow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
//import android.support.v7.internal.widget.AdapterViewICS.OnItemClickListener;

public class Peeps extends ActionBarActivity {
	String temp[] = {"Ss", "gg"};
	ListView list;
	String userName;//, uid;
	String uid = "b9407f30-f5f8-466e-aff9-25556b57fe6d5555522222";
	protected JSONObject mMatchData = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_peeps);
		
		if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                userName= null;
             //   uid = null;
            } else {
                userName= extras.getString("userName");
               // uid = extras.getString("uid");
            }
        } else {
            userName= (String) savedInstanceState.getSerializable("userName");
           // uid = (String) savedInstanceState.getSerializable("uid");
        }
		
		
 	    HotspotMatchTask task = new HotspotMatchTask();
    	task.execute(); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.peeps, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void handleMatches(){
		if (mMatchData == null){
			
		} else {
			try{
				JSONArray jsonMatches = mMatchData.getJSONArray("matches");
				ArrayList<HashMap<String,String>> matches = new ArrayList<HashMap<String,String>>();
				for(int i =0; i<jsonMatches.length(); i++){
					JSONObject match = jsonMatches.getJSONObject(i);
					String username = match.getString("username");
					
					String role = match.getString("role");
					
					HashMap<String, String> matchItem = new HashMap<String, String>();
					matchItem.put("username", username);
					matchItem.put("role", role);
					
					matches.add(matchItem);
				}
				String[] keys = {"username", "role"};
				int[] ids = {android.R.id.text1, android.R.id.text2};
				SimpleAdapter adapter = new SimpleAdapter (this, matches,
						android.R.layout.simple_list_item_2, keys, ids);
				list.setAdapter(adapter);
			} catch (JSONException e) {
				Log.e("List", "Exception caught", e);
			}
				
				
			
		}
	}
	
	class HotspotMatchTask extends AsyncTask<Object, Void, JSONObject> {

		@Override
		protected JSONObject doInBackground(Object... arg0) {
			
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet("http://104.131.105.6:3000/matches/"+userName+"/hotspot/"+uid);
			int responseCode = -1;
			JSONObject jsonResponse = null;
			StringBuilder builder = new StringBuilder();
			
			
			try {
               HttpResponse response = httpclient.execute(httpget);
               StatusLine statusLine = response.getStatusLine();
               responseCode = statusLine.getStatusCode();
               
               if (responseCode == HttpURLConnection.HTTP_OK){
            	   HttpEntity entity = response.getEntity();
            	   InputStream content = entity.getContent();
            	   
            	   BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            	   String line;
            	   while ((line=reader.readLine())!=null){
            		   builder.append(line);
            	   }
               }
               

              	jsonResponse = new JSONObject(builder.toString());
                } catch (IOException e) {
	        	Log.e("app", "exception caught: ",e);
	        }	catch (Exception e){
	        	
	        }
			return jsonResponse;
		}
		
		protected void onPostExecute(JSONObject result){
		 	mMatchData = result;
        	handleMatches();
		}
    	
    }
}
