package com.example.myfirstapp;

/***
 * to do:
 * 1) When starting the application for the first time:
 * 		- Check if sqlite is installed. Fetch and install if not
 * 		- appropriately report error messages (not rooted, sql etc)
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;

import android.app.*;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

//donald
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import Task.TaskManager;

// Test comment
public class MainActivity extends ActionBarActivity {
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Loads the main layout
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
	
	private void loadTasks (){
		TaskManager tm = new TaskManager();
		
		// # DEBUGGING
		// TEST DATA: This test data enters a new task and adds to the records file.
		tm.createNewTask(this, 11, 4, 1985, 00, 00, tm.getSize(), "Nala & Simba like bones.");
		tm.createNewTask(this, 3, 19, 1989, 00, 00, tm.getSize(), "Tom birthday.");
		tm.createNewTask(this, 5, 3, 1987, 00, 00, tm.getSize(), "Sara birthday.");
		tm.createNewTask(this, 11, 20, 1960, 00, 00, tm.getSize(), "Mom birthday.");
		
		tm.loadTasks(this);
 
		// !!! Note that values is still stored here.
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tm.getTaskList().toAdapterStringFormat());
		ListView lv = (ListView)findViewById(R.id.list);
		lv.setAdapter(adapter);
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

	private void newTask() {
		// Prepare the new activity
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		// Obtain a handle to the textBox from the
		//EditText editText = (EditText) findViewById(R.id.edit_message);
		// Get the text from the text field
		//String message = editText.getText().toString();

		//String message = "Test message. MainActivity.newTask()";
		
		// When pressing the button, a new task is created, added to the task
		// list and saved to a file
		// GET THESE VALUES FROM ANOTHER ACTIVITIE'S USER INPUT
		//TaskManager tm = new TaskManager();
		//tm.createNewTask(this, 11, 4, 1985, 16, 23, tm.getSize(), message);

		//intent.putExtra(EXTRA_MESSAGE, message);

		// Start the new activity
		startActivity(intent);
	}

	private static void openSettings(){
		
	}
}
