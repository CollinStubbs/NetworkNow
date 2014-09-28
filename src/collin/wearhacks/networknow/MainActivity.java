package collin.wearhacks.networknow;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
 
public class MainActivity extends Activity {
 
    String userName, passWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
 
        final EditText user = (EditText)findViewById(R.id.username);
        user.setTextColor(0xAA000000);
        
        final EditText pass = (EditText)findViewById(R.id.password);
        pass.setTextColor(0xAA000000);
        
        Button button = (Button) findViewById(R.id.submit);
        button.setOnClickListener(new OnClickListener() {

           public void onClick(View v) {
        	   userName = user.getText().toString();
        	   passWord = pass.getText().toString();
        	   
        	   SessionGetTask swag = new SessionGetTask();
           	   swag.execute();
           }
        });
        Button button2 = (Button) findViewById(R.id.register);
        button2.setOnClickListener(new OnClickListener() {

           public void onClick(View v) {
        	         	   
              
        	     Intent intent = new Intent(getApplicationContext(), Register.class);
        	  startActivity(intent);
        	    
           }
        });
       
 
    }
    class SessionGetTask extends AsyncTask<Object, Void, HttpResponse> {

		@Override
		protected HttpResponse doInBackground(Object... arg0) {
			
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet("http://104.131.105.6:3000/session/" + userName);
			
			
			try {
	            HttpResponse response = httpclient.execute(httpget);
	            return response;
	            
	        } catch (MalformedURLException e){
	        	Log.e("app", "exception caught: ",e);
	        } catch (ClientProtocolException e) {
	        	Log.e("app", "exception caught: ",e);
	        } catch (IOException e) {
	        	Log.e("app", "exception caught: ",e);
	        }
			return null;
		}
		
		protected void onPostExecute(HttpResponse response){
            StatusLine statusLine = response.getStatusLine();
			 if(statusLine.getStatusCode() == HttpURLConnection.HTTP_OK){
	            	Intent intent = new Intent(getApplicationContext(), Homepage.class);
	            	intent.putExtra("userName", userName);
	          	  startActivity(intent);
	            }
			 else{
      		   Toast.makeText(getApplicationContext(), "Invalid User/Pass Combination", 
      				   Toast.LENGTH_LONG).show();
      	   
      	   }
		}
    	
    }

 
}