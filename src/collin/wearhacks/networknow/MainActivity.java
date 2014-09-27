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
 
public class MainActivity extends Activity {
 
    EditText etResponse;
    TextView tvIsConnected;
    int spInt1, spInt2, spInt3;
    ArrayList<String> arrays = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
 
        EditText editText = (EditText)findViewById(R.id.notaspinner);
        //editText.setText("UserName", TextView.BufferType.EDITABLE);	
        editText.setTextColor(0xAA000000);
        
        Button button = (Button) findViewById(R.id.submit);
        button.setOnClickListener(new OnClickListener() {

           public void onClick(View v) {
               // TODO Auto-generated method stub
        	   Toast.makeText(getApplicationContext(), spInt1+""+spInt2+""+spInt3, 
        			   Toast.LENGTH_LONG).show();
        	   
        	   //check if bluetooth enabled
        	   BeaconManager beaconManager = new BeaconManager(getApplicationContext());
           	//PUT and GET array stuffs here

           }
        });
        
        arrays.add("Design");
        arrays.add("Front-End");
        arrays.add("Back-End");
        arrays.add("Android");
        arrays.add("IOS");
        arrays.add("Windows");
        
        
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener(){
        	@Override
        	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            	switch((int)id){
            		case 0:
            			spInt1 = 0;
            			break;
            		case 1:
            			spInt1 = 1;
            			break;
            		case 2:
            			spInt1 = 2;
            			break;
            		case 3:
            			spInt1 = 3;
            			break;
            		case 4:
            			spInt1 = 4;
            			break;
            		case 5:
            			spInt1 = 5;
            			break;
            		
            		}
            	}
        	
        	public void onNothingSelected(AdapterView<?> parent){
            	
            }
        
        });
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(new OnItemSelectedListener(){
        	@Override
        	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            	switch((int)id){
            		case 0:
            			spInt2 = 0;
            			break;
            		case 1:
            			spInt2 = 1;
            			break;
            		case 2:
            			spInt2 = 2;
            			break;
            		case 3:
            			spInt2 = 3;
            			break;
            		case 4:
            			spInt2 = 4;
            			break;
            		case 5:
            			spInt2 = 5;
            			break;
            		
            		}
            	}
        	
        	public void onNothingSelected(AdapterView<?> parent){
            	
            }
        
        });
        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        spinner3.setOnItemSelectedListener(new OnItemSelectedListener(){
        	@Override
        	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            	switch((int)id){
            		case 0:
            			spInt3 = 0;
            			break;
            		case 1:
            			spInt3 = 1;
            			break;
            		case 2:
            			spInt3 = 2;
            			break;
            		case 3:
            			spInt3 = 3;
            			break;
            		case 4:
            			spInt3 = 4;
            			break;
            		case 5:
            			spInt3 = 5;
            			break;
            		
            		}
            	}
        	
        	public void onNothingSelected(AdapterView<?> parent){
            	
            }
        
        });
        
        
        // get reference to the views
       /* etResponse = (EditText) findViewById(R.id.etResponse);
        tvIsConnected = (TextView) findViewById(R.id.tvIsConnected);
 
        // check if you are connected or not
        if(isConnected()){
            tvIsConnected.setBackgroundColor(0xFF00CC00);
            tvIsConnected.setText("You are conncted");
        }
        else{
            tvIsConnected.setText("You are NOT conncted");
        }
 
        // show response on the EditText etResponse 
        etResponse.setText(GET("http://hmkcode.com/examples/index.php"));*/
 
    }
   

 
    public static String GET(String url){
        InputStream inputStream = null;
        String result = "";
        try {
 
            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();
 
            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
 
            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();
 
            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";
 
        } catch (Exception e) {
            Log.d("InputStream", ""+ e.getLocalizedMessage());
        }
 
        return result;
    }
 
    // convert inputstream to String
    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;
 
        inputStream.close();
        return result;
 
    }
 
    // check network connection
    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) 
                return true;
            else
                return false;   
    }
}