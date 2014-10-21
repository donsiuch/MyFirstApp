package com.example.myfirstapp;

import android.os.Bundle;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import Debug.Debug;
import FileManager.FileManager;
import Task.Task;

public class AlarmReceiver extends BroadcastReceiver {

    private Debug debugModule;

    @Override
    public void onReceive(Context context, Intent i){

        // Debugging statement
        Toast.makeText(context, "Received alarm", Toast.LENGTH_LONG).show();

        // Pass the message to the new activity
        Intent intent = new Intent(context, Alarm.class);
        Bundle bundle = new Bundle();
        bundle.putString("description", i.getStringExtra("description"));
        String temp = i.getStringExtra("description");
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void setAlarm(Context context, Task task) {
        String function = "setAlarm(): ";
        debugModule = new Debug(Debug.DEBUG_TIME, FileManager.getFileSaveLocation(context) + "/log.txt");
        debugModule.output(Debug.DEBUG_TIME, function, "Hello");
        debugModule.output(Debug.DEBUG_TIME, function, "world");
        debugModule.internalError(function, "Hello");
        FileManager fm = new FileManager();
        String loc = fm.getFileSaveLocation(context);


        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context,AlarmReceiver.class);
        intent.putExtra("description", task.getDescription());

        // Creates a unique PendingIntent. This is done by passing the unique current time
        PendingIntent pi = PendingIntent.getBroadcast(context, (int)System.currentTimeMillis(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, task.getCalendar().getTimeInMillis(), pi);
        Toast.makeText(context, "Scheduled" + task.toString(), Toast.LENGTH_LONG).show();
    }
}
