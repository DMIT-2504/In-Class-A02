/**
 * 
 */
package ca.nait.labDemo.Redux;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author dgilleland
 * 
 */
public class ReceiveJitterActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receive_jitters);
        getFromJitter();

    }

    private String NL = System.getProperty("line.separator");

    private void getFromJitter() {
        TextView textbox = (TextView) findViewById(R.id.textbox_receive_data);
        try {
            JitterClient jc = new JitterClient();
            List<String> jitters = jc.getJitters();
            for (String posting : jitters)
                textbox.append(posting + NL);

        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e, Toast.LENGTH_LONG).show();
        }
    }
}
