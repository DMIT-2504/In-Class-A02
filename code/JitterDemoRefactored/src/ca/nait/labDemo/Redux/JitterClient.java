package ca.nait.labDemo.Redux;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        
        String line = "";

        while((line = in.readLine()) != null)
        {
            jitters.add(line);
        }
        in.close();
        return jitters;
    }
    
    public StatusLine postToJitter(String text) throws ClientProtocolException, IOException {
        HttpClient client = new DefaultHttpClient();
        HttpPost request = new HttpPost("http://www.youcode.ca/JitterServlet");
        List <NameValuePair> postParameters = new ArrayList<NameValuePair>();
        postParameters.add(new BasicNameValuePair("DATA", text));
        postParameters.add(new BasicNameValuePair("LOGIN_NAME", "DanG"));
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters);
        request.setEntity(formEntity);
        HttpResponse response = client.execute(request);
        return response.getStatusLine();
    }
}