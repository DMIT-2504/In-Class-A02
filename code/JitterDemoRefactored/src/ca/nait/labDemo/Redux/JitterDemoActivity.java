package ca.nait.labDemo.Redux;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
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

public class JitterDemoActivity extends Activity
        implements OnClickListener {
    private static final String TAG = "SendDataActivity";
    private EditText _jitterEditText;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button sendButton = (Button) findViewById(R.id.button_send_data);
        sendButton.setOnClickListener(this);
        Button viewButton = (Button) findViewById(R.id.button_view_jitters);
        viewButton.setOnClickListener(this);

        _jitterEditText = (EditText) findViewById(R.id.textbox_data);
    }

    public void onClick(View view) {
        Log.d(TAG, "button clicked");

        switch (view.getId()) {
        case R.id.button_send_data: {
            if (!postToJitter())
                return;
            _jitterEditText.setText("");
            break;
        }
        }
        Intent intent = new Intent(this, ReceiveJitterActivity.class);
        this.startActivity(intent);
    }

    private boolean postToJitter() {
        String data = _jitterEditText.getText().toString();
        try {
            JitterClient jc = new JitterClient();
            StatusLine result = jc.postToJitter(data);
            if (result.getStatusCode() != 200) {
                Toast.makeText(this,
                        "Error Posting to Jitter: " + result.getReasonPhrase(),
                        Toast.LENGTH_LONG);
                return false;
            }
            return true;
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e, Toast.LENGTH_LONG).show();
            EditText text = (EditText) findViewById(R.id.textbox_data);
            text.setText(e.getMessage());

            return false;
        }
    }
}