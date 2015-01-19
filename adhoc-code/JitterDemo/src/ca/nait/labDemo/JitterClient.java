package ca.nait.labDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.widget.EditText;
import android.widget.Toast;

public class JitterClient {
	public List<String> getJitters()  
            throws URISyntaxException, 
            	   IllegalStateException,
                   IOException {
		BufferedReader in = null;
		List<String> jitters = new ArrayList<String>();
		
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet();
		request.setURI(new URI("http://www.youcode.ca/JSONServlet"));
		HttpResponse response = client.execute(request);
		
		in = new BufferedReader(
				new InputStreamReader(response.getEntity().getContent()));
		
		String line = "";
		
		while((line = in.readLine()) != null) {
			jitters.add(line);
		}
		in.close();
		return jitters;
	}
	
	public StatusLine postToJitter(String data) 
			throws ClientProtocolException, 
			       UnsupportedEncodingException,
			       IOException {
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
			return response.getStatusLine();
		}
}
