package com.example.myfirstapp;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener; // Donald: imported to solve onclick errors
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.os.Build;
import android.content.Intent;



public class DisplayMessageActivity extends ActionBarActivity {	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_display_message);

		// Enable the app icon as the Up button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		TextView text = (TextView)findViewById(R.id.editText1);
		text.setOnClickListener(new OnClickListener() {
		    public void onClick(View v) {
		        System.out.println("Blah");
		      }
		  });
		
		CalendarView calendar = (CalendarView)findViewById(R.id.calendarView1);
		calendar.setOnClickListener(new OnClickListener() {
		    public void onClick(View v) {
		        System.out.println("Bleh");
		      }
		  });
/*		
		// Get the "tunnel" intermediate object
		Intent intent = getIntent();
		String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		
		TextView textView = new TextView(this);
		textView.setTextSize(40);
		textView.setText(message);
		
		// Set the text as the activity layout
		setContentView(textView);
*/
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_display_message,
					container, false);
			return rootView;
		}
	}

}
