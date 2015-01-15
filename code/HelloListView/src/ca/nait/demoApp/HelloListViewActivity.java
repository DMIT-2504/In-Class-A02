package ca.nait.demoApp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class HelloListViewActivity extends Activity
                   implements OnClickListener {
	private List<String> _todoItems = new ArrayList<String>();
	private Button _addButton;
	private EditText _userInput;
	private ListView _todoListView;
	private ArrayAdapter<String> _adapter;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        _addButton = (Button) findViewById(R.id.button1);
        _addButton.setOnClickListener(this);
        
        _userInput = (EditText) findViewById(R.id.editText1);
        _todoListView = (ListView) findViewById(R.id.todoList);
        
        _adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, _todoItems);
    }

	public void onClick(View arg0) {
    String item = _userInput.getText().toString();
    
    if(Str.isNotEmptyString(item)) {
    	_todoItems.add(item);
    	_todoListView.setAdapter(_adapter);
    	_userInput.setText("");
    } else {
    	Toast.makeText(this, "Please enter some text before clicking the button.", Toast.LENGTH_LONG).show();
    }
	}
}