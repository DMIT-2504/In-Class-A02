package ca.nait.demoApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HelloDataSharingActivity extends Activity
                                      implements OnClickListener {
	private static final String TAG = "SendDataActivity";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	// 0) Call the base version of onCreate, and set the view for this activity
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		// 1) Wire up listeners (event handlers)
		Button sendButton = (Button) findViewById(R.id.button_send_data);
		sendButton.setOnClickListener(this);
        
        Button toastButton = (Button)findViewById(R.id.button_show_toast);
        toastButton.setOnClickListener(this);
    }
    
    public void onClick(View arg0) 
	{
		Log.d(TAG,"button clicked");

		// Handle the send button's click
		// 0) Get the user input
		EditText text = (EditText)findViewById(R.id.text_view_data);
		String data = text.getText().toString();

		if(arg0.getId() == R.id.button_show_toast)
    		showTextInToast(data);
    	if(arg0.getId() == R.id.button_send_data)
    		sendTextToActivity(data);
	}

	private void sendTextToActivity(String data) {
		Log.d(TAG,"sending to activity");
		// 1) Set up the Intent
		// an Intent is a message to another activity
		Intent myIntent = new Intent(this, ReceiveDataActivity.class);
		// a Bundle is a collection of key/value pairs
		Bundle myData = new Bundle();
		myData.putString("PREFIX","You typed: ");
		myData.putString("DATA",data);
		myIntent.putExtras(myData);

		// 2) Start an activity
		this.startActivity(myIntent); 
	}

	private void showTextInToast(String data) {
		Log.d(TAG,"showing toast");
		// 1) Show the text in a toast
		Toast.makeText(this, "You entered: " + data, Toast.LENGTH_LONG).show();
	}
}