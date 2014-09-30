package collin.wearhacks.networknow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;



import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
 
public class Homepage extends Activity {
 LinearLayout lay;
 ImageView img;
 String userName;
 String role;
 String wants;
 String uid;
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_homepage);
        
        
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                userName= null;
                uid = null;
            } else {
                userName= extras.getString("userName");
                uid = extras.getString("uid");
            }
        } else {
            userName= (String) savedInstanceState.getSerializable("userName");
            uid= (String) savedInstanceState.getSerializable("uid");
        }
        
        lay = (LinearLayout)findViewById(R.id.layoutID);
        img = (ImageView)findViewById(R.id.logoHome);
        
        ImageButton pro = (ImageButton) findViewById(R.id.profileButton);
        ImageButton find = (ImageButton) findViewById(R.id.findButton);
        ImageButton log = (ImageButton) findViewById(R.id.logout);
        final View vv = (View) findViewById(R.id.view);
        		vv.setVisibility(View.GONE);
        
        final Spinner spin1 = (Spinner) findViewById(R.id.spinner1);
        final Spinner spin2 = (Spinner) findViewById(R.id.spinner2);
        spin1.setVisibility(View.GONE);
        spin2.setVisibility(View.GONE);
       
        
        
        pro.setOnClickListener(new OnClickListener() {

	           
			@SuppressWarnings("deprecation")
			public void onClick(View v) {
					
					
	        	   img.setImageDrawable(null);
	        	   lay.removeAllViews();
	        	   spin1.setVisibility(View.VISIBLE);
	        	   
	        	   
	        	   spin2.setVisibility(View.VISIBLE);
	        	   vv.setVisibility(View.VISIBLE);
	        	
	        	   
	        	   TextView tv = new TextView(getApplicationContext());
					tv.setText("Please Add Your Skills");
					tv.setTextSize(25);
					tv.setPadding(10, 70, 20, 10);
					tv.setTypeface(null, Typeface.BOLD);
					tv.setTextColor(Color.BLACK);
					tv.setGravity(Gravity.CENTER_HORIZONTAL);
					lay.addView(tv);
					
					LinearLayout li = new LinearLayout(getApplicationContext());
					li.setOrientation(LinearLayout.HORIZONTAL);
					li.setGravity(Gravity.CENTER);
					li.setLayoutParams( new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
					
	        	   Button but = new Button(getApplicationContext());
	        	   but.setText("Update");
	        	   but.setTextColor(Color.BLACK);
	        	   but.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));
	        	   but.setPadding(25, 10, 25, 10);
	        	   but.setBackgroundResource(R.drawable.border);
	        	   lay.addView(spin1);
	        	   
	        	   TextView tv2 = new TextView(getApplicationContext());
					tv2.setText("What Do You Need?");
					tv2.setTextSize(25);
					tv2.setPadding(10, 70, 20, 10);
					tv2.setTypeface(null, Typeface.BOLD);
					tv2.setTextColor(Color.BLACK);
					tv2.setGravity(Gravity.CENTER_HORIZONTAL);
					lay.addView(tv2);
	        	   lay.addView(spin2);
	        	   lay.addView(li);
	        	   li.addView(but);
	        	   
	        	   but.setOnClickListener(new View.OnClickListener(){
	        		   public void onClick(View v){
	        			   role = spin1.getSelectedItem().toString();
	        			   wants = spin2.getSelectedItem().toString();
	        			   PutUserTask task = new PutUserTask();
	        			   task.execute();
	        		   }
	        	   });
	        	   
	        	    
	           }
	        });
        find.setOnClickListener(new OnClickListener() {

	           public void onClick(View v) {
	        	   img.setImageDrawable(null);
	        	   lay.removeAllViews();
	        
	        	   Intent intent = new Intent(getApplicationContext(), ListBeaconsActivity.class);
	               intent.putExtra(ListBeaconsActivity.EXTRAS_TARGET_ACTIVITY, NotifyDemoActivity.class.getName());
	               intent.putExtra("userName", userName);
	               startActivity(intent);
	        	   //findEstimote();
	           }
	        });
        log.setOnClickListener(new OnClickListener() {

	           public void onClick(View v) {
	        	   img.setImageDrawable(null);
	        	   lay.removeAllViews();
	        	   //logout();
	           }
	        });

    }
    class PutUserTask extends AsyncTask<Object, Void, HttpResponse> {

  	  @Override
  		protected HttpResponse doInBackground(Object... arg0) {
  			
  			HttpClient httpclient = new DefaultHttpClient();
  			HttpPut httpput = new HttpPut("http://104.131.105.6:3000/edituser/");

			try {
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("username", userName));
                //nameValuePairs.add(new BasicNameValuePair("password", passWord));
                nameValuePairs.add(new BasicNameValuePair("role", role));
                nameValuePairs.add(new BasicNameValuePair("wants", wants));
                httpput.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	            
             // Execute HTTP Post Request
               HttpResponse response = httpclient.execute(httpput);
               return response;
                } catch (IOException e) {
	        	Log.e("app", "exception caught: ",e);
	        }
  			
  			try {
  	            HttpResponse response = httpclient.execute(httpput);
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
  				Intent intent = new Intent(getApplicationContext(), Peeps.class);
	        	   intent.putExtra("userName", userName);
	        	   intent.putExtra("uid", uid);
	               startActivity(intent);
  	            }
  			 else{
      	   
      	   }
  		}
        
      }

}