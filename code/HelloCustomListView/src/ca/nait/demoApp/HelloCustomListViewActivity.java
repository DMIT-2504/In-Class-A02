package ca.nait.demoApp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class HelloCustomListViewActivity extends Activity 
                                   implements OnClickListener {
	// Fields to reference the controls and the data that I want to access/use in my Activity's methods
    private List<HashMap <String,String> > _todoItems = new ArrayList<HashMap <String,String> >();
    private Button _addButton;
    private EditText _taskInput;
    private EditText _dueDateInput;
    private EditText _personNameInput;
    private ListView _todoListView;
    private SimpleAdapter _adapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Get references to my controls on the form
        _addButton = (Button) findViewById(R.id.button1);
        _addButton.setOnClickListener(this); // wire up the event handler for click events

        _taskInput = (EditText) findViewById(R.id.editToDoItem);
        _dueDateInput = (EditText) findViewById(R.id.editDueDate);
        _personNameInput = (EditText) findViewById(R.id.editPersonResponsible);
        _todoListView = (ListView) findViewById(R.id.todoList);

        // Setting up the mapping that will be used by the adapter to associate data from the HashMap
        // to the controls on the ListView's custom row structure.
        String[] fields = 
                new String[]{ "sender","text", "date"};
        int [] ids = new int[]{R.id.sender,R.id.text,R.id.date};
        _adapter = new SimpleAdapter(this, _todoItems,
                R.layout.custom_row, fields, ids);
    }

    public void onClick(View arg0) {
        String item = _taskInput.getText().toString();
        String name = _personNameInput.getText().toString();
        String dueDate = _dueDateInput.getText().toString();

        if (Str.isNotEmptyString(item)) {
            // Create a HashMap item to add to the list            
            HashMap<String, String> todoItem = new HashMap<String, String>();
            todoItem.put("sender", name);
            todoItem.put("text", item);
            todoItem.put("date", dueDate);
            
            // Add to list and set the adapter 
            _todoItems.add(todoItem);
            _todoListView.setAdapter(_adapter);
            
            // Clear out the user input
            _taskInput.setText("");
            _personNameInput.setText("");
            _dueDateInput.setText("");
        } else {
            Toast.makeText(this,
                    "Please enter some text before clicking the button.",
                    Toast.LENGTH_LONG).show();
        }
    }
}