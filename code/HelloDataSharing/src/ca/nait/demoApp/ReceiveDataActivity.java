package ca.nait.demoApp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ReceiveDataActivity extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receive_data);
        
        Bundle bundle = getIntent().getExtras();
        String prefix = bundle.getString("PREFIX");
        String data = bundle.getString("DATA");
        
        TextView textboxPrefix = (TextView)findViewById(R.id.textbox_receive_data_prefix);
        textboxPrefix.setText(prefix);
 
        TextView textbox = (TextView)findViewById(R.id.textbox_receive_data);
        textbox.setText(data);
    }
    
}