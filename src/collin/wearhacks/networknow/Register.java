package collin.wearhacks.networknow;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
	        	   if(passReg.toString().equals(passConfirmReg.toString())){
	        		   
	        	   //do http stuff here
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
}
