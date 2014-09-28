package collin.wearhacks.networknow;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.Utils;


/**
 * Displays basic information about beacon.
 *
 * @author wiktor@estimote.com (Wiktor Gworek)
 */
public class LeDeviceListAdapter extends BaseAdapter {

  private ArrayList<Beacon> beacons;
  private LayoutInflater inflater;
  private Context context;

  String uid; 
  String userName;

  public void setUserName(String name){
    userName = name;
  }

  public LeDeviceListAdapter(Context context) {
    this.inflater = LayoutInflater.from(context);
    this.beacons = new ArrayList<Beacon>();
    this.context = context;
  }

  public void replaceWith(Collection<Beacon> newBeacons) {
    this.beacons.clear();
    this.beacons.addAll(newBeacons);
    notifyDataSetChanged();
  }

  @Override
  public int getCount() {
    return beacons.size();
  }

  @Override
  public Beacon getItem(int position) {
    return beacons.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View view, ViewGroup parent) {
    view = inflateIfRequired(view, position, parent);
    bind(getItem(position), view);
    return view;
  }

  private void bind(Beacon beacon, View view) {
    ViewHolder holder = (ViewHolder) view.getTag();
    // Print the UUID and Distance
    Double distance = Math.round(Utils.computeAccuracy(beacon) * 100.0) / 100.0;
    holder.macTextView.setText(String.format("UUID: "+beacon.getProximityUUID()+beacon.getMajor()+beacon.getMinor()+"\n Distance: "+distance+" meters"));
    
    uid = beacon.getProximityUUID()+beacon.getMajor()+beacon.getMinor();


  }

  private View inflateIfRequired(View view, int position, ViewGroup parent) {
    if (view == null) {
      view = inflater.inflate(R.layout.device_item, null);
      view.setTag(new ViewHolder(view));
    }
    return view;
  }

  static class ViewHolder {
    final TextView macTextView;
    final TextView majorTextView;
    final TextView minorTextView;
    final TextView measuredPowerTextView;
    final TextView rssiTextView;

    ViewHolder(View view) {
      macTextView = (TextView) view.findViewWithTag("mac");
      majorTextView = (TextView) view.findViewWithTag("major");
      minorTextView = (TextView) view.findViewWithTag("minor");
      measuredPowerTextView = (TextView) view.findViewWithTag("mpower");
      rssiTextView = (TextView) view.findViewWithTag("rssi");
    }
  }


  class PutHotTask extends AsyncTask<Object, Void, HttpResponse> {

	  @Override
		protected HttpResponse doInBackground(Object... arg0) {
			
			HttpClient httpclient = new DefaultHttpClient();
			HttpPut httpget = new HttpPut("http://104.131.105.6:3000/hotspot/"+uid + "/username/" + userName);
			
			
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
	            	Intent intent = new Intent(context.getApplicationContext(), Homepage.class);
	            	intent.putExtra("userName", userName);
	            	intent.putExtra("uid", uid);
	          	  context.startActivity(intent);
	            }
			 else{
    		   Toast.makeText(context.getApplicationContext(), "Invalid User/Pass Combination", 
    				   Toast.LENGTH_LONG).show();
    	   
    	   }
		}
      
    }
}
