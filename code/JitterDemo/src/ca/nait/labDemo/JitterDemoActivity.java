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

public class JitterDemoActivity extends Activity 
                                implements OnClickListener {
    private static final String TAG = "SendDataActivity";
    
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button sendButton = (Button)findViewById(R.id.button_send_data);
        sendButton.setOnClickListener(this);
        Button viewButton = (Button)findViewById(R.id.button_view_jitters);
        viewButton.setOnClickListener(this);
    }
    
    public void onClick(View view) 
    {
        Log.d(TAG,"button clicked");

        switch(view.getId())
    {
        case R.id.button_send_data:
        {
        EditText text = (EditText)findViewById(R.id.textbox_data);
        String data = text.getText().toString();
        if(!postToJitter(data))
            return;
        text.setText("");
        break;
        }
    }
    Intent intent = new Intent(this,ReceiveJitterActivity.class);
    this.startActivity(intent);
    }
    
    private boolean postToJitter(String string) 
    {
    try
    {
        HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost("http://www.youcode.ca/JitterServlet");
            List <NameValuePair> postParameters = new ArrayList<NameValuePair>();
            postParameters.add(new BasicNameValuePair("DATA", string));
            postParameters.add(new BasicNameValuePair("LOGIN_NAME", "DanG"));
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters);
            request.setEntity(formEntity);
            HttpResponse response = client.execute(request);
            return true;
    }
    catch(Exception e)
    {
        Toast.makeText(this, "Error: " + e, Toast.LENGTH_LONG).show();
        EditText text = (EditText)findViewById(R.id.textbox_data);
        text.setText(e.getMessage());

        return false;
        }
    }
}