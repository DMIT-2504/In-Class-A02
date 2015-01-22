package ca.nait.labDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
	public List<HashMap<String, String>> getJitterDetails() 
			throws IllegalStateException, URISyntaxException, IOException {
		List<HashMap<String, String>> jitters = new ArrayList<HashMap<String, String>>();
		// .....
		HttpResponse response = callJitterWebService("http://www.youcode.ca/JitterServlet");
		
		BufferedReader in = new BufferedReader(
							new InputStreamReader(response.getEntity().getContent()));
		
		String line = "";
		
		while((line = in.readLine()) != null) {
			// Name of person
			String name = line;
			// Post
			String post = in.readLine();
			// Date
			String date = in.readLine();
			
			// Make a hashmap and put the items in it
			HashMap<String, String> data = new HashMap<String, String>();
			data.put("personName", name);
			data.put("postedText", post);
			data.put("postedOnDate", date);
			
			// Add it to my list
			jitters.add(data);
		}
		in.close();
		return jitters;
	}
	
	private HttpResponse callJitterWebService(String url) 
            throws URISyntaxException, 
     	   IllegalStateException,
            IOException {
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet();
		request.setURI(new URI(url));
		HttpResponse response = client.execute(request);
		return response;
	}
	
	public List<String> getJitters()  
            throws URISyntaxException, 
            	   IllegalStateException,
                   IOException {
		BufferedReader in = null;
		List<String> jitters = new ArrayList<String>();
		
		HttpResponse response = callJitterWebService("http://www.youcode.ca/JSONServlet");
//		HttpClient client = new DefaultHttpClient();
//		HttpGet request = new HttpGet();
//		request.setURI(new URI("http://www.youcode.ca/JSONServlet"));
//		HttpResponse response = client.execute(request);
		
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
