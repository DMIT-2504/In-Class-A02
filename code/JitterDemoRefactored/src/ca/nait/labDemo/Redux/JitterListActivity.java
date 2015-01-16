package ca.nait.labDemo.Redux;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class JitterListActivity extends ListActivity {
    public void onCreate(Bundle savedStateInstance) 
    {
        super.onCreate(savedStateInstance);
        displayJitters();
        
    }
    public void displayJitters()
    {
        try
        {
            JitterClient jc = new JitterClient();
            ArrayAdapter<String> adapter;
            adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, jc.getJitters());
            setListAdapter(adapter);
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Error: " + e, Toast.LENGTH_LONG).show();
        }
    }
}
