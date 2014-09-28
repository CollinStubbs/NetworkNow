package collin.wearhacks.networknow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;


/**
 * Displays basic information about beacon.
 *
 * @author wiktor@estimote.com (Wiktor Gworek)
 */
public class LeDeviceListAdapter extends BaseAdapter {

  private ArrayList<Beacon> beacons;
  private LayoutInflater inflater;

  String uid; 
  String userName;

  public void setUserName(String name){
    userName = name;
  }

  public LeDeviceListAdapter(Context context) {
    this.inflater = LayoutInflater.from(context);
    this.beacons = new ArrayList<Beacon>();
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
    
    uid = beacon.getProximityUUID()+beacon.getMajor()+beacon.getMinor()


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


  class PutHotTask extends AsyncTask<Object, Void, JSONObject> {

    @Override
    protected JSONObject doInBackground(Object... arg0) {
      
      HttpClient httpclient = new DefaultHttpClient();
      HttpGet httpget = new HttpGet("http://104.131.105.6:3000/hotspot"+uid+"/username/"+userName);
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
          } catch (Exception e){
            
          }
      return jsonResponse;
    }
    
    protected void onPostExecute(JSONObject result){
      mMatchData = result;
          handleMatches();
    }
      
    }
}
