package collin.wearhacks.networknow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;



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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
 
public class Homepage extends Activity {
 LinearLayout lay;
 ImageView img;
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_homepage);
        
        lay = (LinearLayout)findViewById(R.id.layoutID);
        img = (ImageView)findViewById(R.id.logoHome);
        
        ImageButton pro = (ImageButton) findViewById(R.id.profileButton);
        ImageButton find = (ImageButton) findViewById(R.id.findButton);
        ImageButton log = (ImageButton) findViewById(R.id.logout);
        
        final Spinner spin1 = (Spinner) findViewById(R.id.spinner1);
        final Spinner spin2 = (Spinner) findViewById(R.id.spinner2);
        final Spinner spin3 = (Spinner) findViewById(R.id.spinner3);
        
         spin1.setVisibility(View.GONE);
        spin2.setVisibility(View.GONE);
         spin3.setVisibility(View.GONE);
        
        
        pro.setOnClickListener(new OnClickListener() {

	           public void onClick(View v) {
	        	   img.setImageDrawable(null);
	        	   spin1.setVisibility(View.VISIBLE);
	        	   spin2.setVisibility(View.VISIBLE);
	        	   spin3.setVisibility(View.VISIBLE);
	        	   //profile();
	           }
	        });
        find.setOnClickListener(new OnClickListener() {

	           public void onClick(View v) {
	        	   img.setImageDrawable(null);
	        	   
	        	   //findEstimote();
	           }
	        });
        log.setOnClickListener(new OnClickListener() {

	           public void onClick(View v) {
	        	   img.setImageDrawable(null);
	        	   //logout();
	           }
	        });

    }


}