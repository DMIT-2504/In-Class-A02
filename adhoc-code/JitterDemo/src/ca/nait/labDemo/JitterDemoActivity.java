package ca.nait.labDemo;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JitterDemoActivity extends Activity implements OnClickListener {
	// Fields
	private static final String TAG = "SendDataActivity";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Tip: Wrap this inside a try/catch where you show the error in the
		// catch block
		// using a Toast
		// Wire up our buttons to have this class listen for button clicks
		Button sendButton = (Button) findViewById(R.id.button_send_data);
		sendButton.setOnClickListener(this);
		Button viewButton = (Button) findViewById(R.id.button_view_jitters);
		viewButton.setOnClickListener(this);
	}

	public void onClick(View arg0) {
		Log.d(TAG, "button clicked");

		switch (arg0.getId()) {
			case R.id.button_send_data: {
				EditText text = (EditText) findViewById(R.id.textbox_data);
				String data = text.getText().toString();
				if (!postToJitter(data))
					return;
				text.setText("");
				break;
			}
		}
		// Do in all cases - start the other activity
		Intent intent = new Intent(this, ReceiveJitterActivity.class);
		this.startActivity(intent);

	}

	private boolean postToJitter(String data) {
		try {
			// Make my client object
			HttpClient client = new DefaultHttpClient();
			// Create a Post object (with form data that will be attached to the post)
			HttpPost request = new HttpPost("http://www.youcode.ca/JitterServlet");
			List <NameValuePair> postParameters = new ArrayList<NameValuePair>();
			postParameters.add(new BasicNameValuePair("DATA", data));
			postParameters.add(new BasicNameValuePair("LOGIN_NAME", "DanG"));
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters);
			request.setEntity(formEntity);
			
			// send the request and get the response
			HttpResponse response = client.execute(request);
			
			return true;
		} catch(Exception e) {
			Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG);
			EditText text = (EditText) findViewById(R.id.textbox_data);
			text.setText(e.getMessage());
			
			return false;
		}
		
	}
}

















