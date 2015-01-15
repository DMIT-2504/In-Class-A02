package ca.nait.demoApp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class HelloMenuActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.demo_menu, menu);
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String message;
        switch (item.getItemId()) {
        case R.id.first_menu_item: {
            message = "We're sorry, that number is out of service.";
            break;
        }
        case R.id.second_menu_item: {
            message = "I can add: 2 + 2 = 4";
            break;
        }
        case R.id.third_menu_item: {
            message = "Write on!";
            break;
        }
        case R.id.fourth_menu_item: {
            message = "Try that again...";
            break;
        }
        case R.id.last_menu_item: {
            message = "Goodnight.";
            break;
        }
        default: {
            message = "Unsupported menu option";
            break;
        }
        }
        Say(message);

        return true;
    }

    private void Say(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}