package com.example.myfirstapp;

/***
 * to do:
 * 1) When starting the application for the first time:
 * 		- Check if sqlite is installed. Fetch and install if not
 * 		- appropriately report error messages (not rooted, sql etc)
 */

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import InheritedViews.CustomArrayAdapter;
import InheritedViews.CustomListView;
import SQLite.SQLiteHelperTaskTable;
import Task.TaskManager;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Creates a window and places the UI in it
		setContentView(R.layout.activity_main);
		
		// Populates the main activity layout area
		loadTasks();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);
	    return super.onCreateOptionsMenu(menu);
	}

    /**
     * Load all the tasks in the database and populate
     */
	private void loadTasks (){
        final TaskManager tm = new TaskManager();
		tm.loadTasks(this);

        // Passes the resource custom_list_view_row to CustomArrayAdapter
        // The fields that can be populated by CustomArrayAdapter are defined in this resource file
        // This can be though of as describing to the adapter what the rows should look like
        CustomArrayAdapter adapter = new CustomArrayAdapter(this, R.layout.custom_list_view_row, tm.getTaskList().getTaskLinkedList());

        // Defines the CustomListView.
        // This may NOT be needed since we defined the rows above
        final CustomListView lv = (CustomListView)findViewById(R.id.customListView);

        // Tie CustomListView with the adapter custom rows
        lv.setAdapter(adapter);

        // Set the event handler
        lv.setOnItemLongClickListener(new android.widget.AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                View child = adapterView.getChildAt(i);

                if (!lv.getDelete()) {
                    child.setBackgroundColor(Color.RED);
                    lv.setDelete(true);
                    Toast.makeText(getApplicationContext(), "Marked for deletion.", Toast.LENGTH_LONG).show();
                    return true;
                }

                child.setBackgroundColor(Color.TRANSPARENT);
                lv.setDelete(false);
                Toast.makeText(getApplicationContext(), "Removing mark for deletion.", Toast.LENGTH_LONG).show();
                return false;
            }
        });

        lv.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // If marked for deletion, delete.
                if (lv.getDelete()) {
                    SQLiteHelperTaskTable sql = new SQLiteHelperTaskTable(getApplicationContext());

                    // Get the nested view: custom_list_view_row.xml
                    // At index zero since it is the first and only child
                    View row = lv.getChildAt(0);

                    // Get the textView child embedded in custom_list_view_row.xml
                    TextView _id_textView = (TextView)row.findViewById(R.id._id);

                    // Convert the text to a lon
                    long _id = Long.parseLong(_id_textView.getText().toString());

                    sql.deleteTask(_id);

                    // INSERT CODE TO REFRESH THE LISTVIEW!
                }
            }
        });
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		/*// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);*/
		 // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_new:
	            newTask();
	            return true;
	        case R.id.action_settings:
	            openSettings();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

    // Create a new task and close this activity
	private void newTask() {
		startActivity(new Intent(this, DisplayMessageActivity.class));
        finish();
	}

	private static void openSettings(){
		
	}
}
