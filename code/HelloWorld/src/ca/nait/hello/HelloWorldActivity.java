package ca.nait.hello;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HelloWorldActivity extends Activity
                                implements OnClickListener {
    private static final String TAG = "SendDataActivity";
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Don't forget this code, which wires up my Activity class
        // to handle the button click of my View
        Button sendButton = (Button)findViewById(R.id.button_send_data);
        sendButton.setOnClickListener(this);
    }
    
    public void onClick(View arg0) 
    {
        Log.d(TAG,"button clicked");
        EditText text = (EditText)findViewById(R.id.text_view_data);
        String data = text.getText().toString();
        
        Toast.makeText(this, "You entered: " + data, Toast.LENGTH_LONG).show();
    }
}






