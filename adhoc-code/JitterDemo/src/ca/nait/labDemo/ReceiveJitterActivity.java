package ca.nait.labDemo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
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
		TextView textBox = (TextView)findViewById(R.id.textbox_receive_data);
		try {
			JitterClient jc = new JitterClient();
			List<String> jitters = jc.getJitters();
			for (String posting : jitters)
				textBox.append(posting + NL);
		} catch(Exception e) {
			Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG);
		}
	}

}


















