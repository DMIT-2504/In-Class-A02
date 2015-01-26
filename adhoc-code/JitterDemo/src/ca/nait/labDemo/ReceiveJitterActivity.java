package ca.nait.labDemo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ReceiveJitterActivity extends Activity {
	// fields
	private final String NL = System.getProperty("line.separator");
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.receive_jitters);
		getFromJitter();
	}

	private void getFromJitter() {
		ListView customListView = (ListView)findViewById(R.id.listview_receive_data);
		try {
			JitterClient jc = new JitterClient();
			List<HashMap<String,String>> jitters = jc.getJitterDetails();
			// Mapping to the custom listview involves creating 
			// two parallel arrays, one being the keys in the hashmap
			// and the other being the ids of the controls in the custom row.
			String[] keys = new String[] { JitterClient.KEY_PERSON_NAME,
					                       JitterClient.KEY_POSTED_ON_DATE,
					                       JitterClient.KEY_POSTED_TEXT};
			int[] rowIds = new int[] { R.id.sender,
					                   R.id.posted_date,
					                   R.id.jitter_post };
			SimpleAdapter adapter = new SimpleAdapter(this,
					                                  jitters,
					                                  R.layout.jitter_row_layout,
					                                  keys,
					                                  rowIds);

			customListView.setAdapter(adapter);
//			List<String> jitters = jc.getJitters();
//			for (String posting : jitters)
//				; //textBox.append(posting + NL);
		} catch(Exception e) {
			Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG);
		}
	}

}


















