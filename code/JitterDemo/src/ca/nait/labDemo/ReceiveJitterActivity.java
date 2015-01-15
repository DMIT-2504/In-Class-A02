/**
 * 
 */
package ca.nait.labDemo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author dgilleland
 *
 */
public class ReceiveJitterActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receive_jitters);
        getFromJitter();
    
    }
	private void getFromJitter() 
	{
		BufferedReader in = null;
		TextView textbox = (TextView)findViewById(R.id.textbox_receive_data);
		try
		{
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet();
			request.setURI(new URI("http://www.youcode.ca/JitterServlet"));
			HttpResponse response = client.execute(request);
			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			
			while((line = in.readLine()) != null)
			{
				sb.append(line + NL);
				System.out.println(line);
				textbox.append(line + "\n");
			}
			in.close();
		}
		catch(Exception e)
		{
			Toast.makeText(this, "Error: " + e, Toast.LENGTH_LONG).show();
		}
	}
}
