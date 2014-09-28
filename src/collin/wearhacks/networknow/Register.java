package collin.wearhacks.networknow;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends ActionBarActivity {
	String userName, passWord;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_register);
		  	final EditText userReg = (EditText)findViewById(R.id.usernameReg);
	        userReg.setTextColor(0xAA000000);
	        
	        final EditText passReg = (EditText)findViewById(R.id.passwordReg);
	        passReg.setTextColor(0xAA000000);
	        
	        final EditText passConfirmReg = (EditText) findViewById(R.id.passwordConfReg);
	        passConfirmReg.setTextColor(0xAA000000);
	        
	        Button buttonSub = (Button) findViewById(R.id.submitReg);
	        buttonSub.setOnClickListener(new OnClickListener() {

		           public void onClick(View v) {
		        	  //if pass and confirm pass are equal
		        	   if(passReg.getText().toString().equals(passConfirmReg.getText().toString())){
		        		  
		        		   userName = userReg.getText().toString();
		        		   passWord = passReg.getText().toString();
		        		   
		        		   UserPostTask swaggy = new UserPostTask();
		               	   swaggy.execute();
		        	   }
		        	   else{
		        		   Toast.makeText(getApplicationContext(), "Please make sure your passwords are the same.", 
		        				   Toast.LENGTH_LONG).show();   
		        	   }
		           }
		        });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
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
	class UserPostTask extends AsyncTask<Object, Void, HttpResponse> {

		@Override
		protected HttpResponse doInBackground(Object... arg0) {
			
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://104.131.105.6:3000/user");
			
			
			try {
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("username", userName));
                nameValuePairs.add(new BasicNameValuePair("password", passWord));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	            
             // Execute HTTP Post Request
               HttpResponse response = httpclient.execute(httppost);
               return response;
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
	      		   Toast.makeText(getApplicationContext(), "Username is Invalid or Taken", 
	      				   Toast.LENGTH_LONG).show();
	      	   }
		}
    	
    }
}
