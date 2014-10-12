package com.example.myfirstapp;

import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.provider.AlarmClock;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.MenuItem;

// Views
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.text.TextWatcher;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import Task.Task;
import Task.TaskManager;

public class DisplayMessageActivity extends ActionBarActivity {

    Task currentTask;
    TaskManager tm;

    public DisplayMessageActivity(){
        currentTask = new Task();
        tm = new TaskManager();
    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        // Enable the app icon as the Up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		setContentView(R.layout.activity_display_message);

        // Make the root layout request focus.
        // Prevents the description from gaining focus first and the keyboard from displaying
        LinearLayout ll = (LinearLayout)findViewById(R.id.rootDisplayMessageActivityLayout);
        ll.requestFocus();

        final CalendarView calendar = (CalendarView) findViewById(R.id.calendarView1);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int day) {
                currentTask.setYear(year);
                currentTask.setMonth(month);
                currentTask.setDay(day);
            }
        });

        final TimePicker time = (TimePicker) findViewById(R.id.timePicker1);
        time.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePickerView, int hour, int minute) {
                currentTask.setHour(hour);
                currentTask.setMinute(minute);
            }
        });

        final EditText textField = (EditText) findViewById(R.id.editText1);
        textField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                // Does nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                currentTask.setDescription(textField.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Does nothing
            }
        });

        final Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = tm.storeInDatabase(getApplicationContext(), currentTask);
                AlarmReceiver alarmReceiver = new AlarmReceiver();
                alarmReceiver.setAlarm(getBaseContext(), currentTask);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
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
			return inflater.inflate(R.layout.fragment_display_message, container, false);
		}
	}
}
