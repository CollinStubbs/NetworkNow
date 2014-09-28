package collin.wearhacks.networknow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
 
public class MainActivity extends Activity {
 
    String userName, passWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ww
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
        	   
              //have this open new activity
        	   //HTTP STUFF
        	   /* if(usernameFound && passwordMatches){
        	    * 	Intent intent = new Intent(getApplicationContext(), Homepage.class);
        	  startActivity(intent);
        	    * }else{
        	    * Intent intent = new Intent(getApplicationContext(), Register.class);
        	  startActivity(intent);
        	    * }
        	  
        	  */
           }
        });
        
       
 
    }
   

 
}