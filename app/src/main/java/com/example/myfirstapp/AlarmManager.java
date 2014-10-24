/**
 * Sets alarms.
 *
 * To do: Delete alarms.
 */

package com.example.myfirstapp;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import Debug.Debug;
import FileManager.FileManager;
import Task.Task;

public class AlarmManager {

    private Debug debugModule;

    public void setAlarm(Context context, Task task) {
        String function = "setAlarm(): ";
        debugModule = new Debug(Debug.DEBUG_TIME, FileManager.getFileSaveLocation(context) + "/log.txt");
        debugModule.output(Debug.DEBUG_TIME, function, "Hello");
        debugModule.output(Debug.DEBUG_TIME, function, "world");
        debugModule.internalError(function, "Hello");
        FileManager fm = new FileManager();
        String loc = fm.getFileSaveLocation(context);


        android.app.AlarmManager alarmManager = (android.app.AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context,AlarmReceiver.class);
        intent.putExtra("description", task.getDescription());

        // Creates a unique PendingIntent. This is done by passing the unique current time
        PendingIntent pi = PendingIntent.getBroadcast(context, (int)System.currentTimeMillis(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setExact(android.app.AlarmManager.RTC_WAKEUP, task.getCalendar().getTimeInMillis(), pi);
        Toast.makeText(context, "Scheduled" + task.toString(), Toast.LENGTH_LONG).show();
    }
}
