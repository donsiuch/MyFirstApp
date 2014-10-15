package com.example.myfirstapp;

/***
 * to do:
 * 1) When starting the application for the first time:
 * 		- Check if sqlite is installed. Fetch and install if not
 * 		- appropriately report error messages (not rooted, sql etc)
 */

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

//donald
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import Task.TaskManager;

// Test comment
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
        TaskManager tm = new TaskManager();
		tm.loadTasks(this);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tm.getTaskList().toAdapterStringFormat());
		final ListView lv = (ListView)findViewById(R.id.list);
		lv.setAdapter(adapter);

        // Simple click go to details?
        lv.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        // Long click change colors
        lv.setOnLongClickListener(new AdapterView.OnLongClickListener(){

            @Override
            public boolean onLongClick(View view) {
                return false;
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
