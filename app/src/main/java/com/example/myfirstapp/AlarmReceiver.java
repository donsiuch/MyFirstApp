package com.example.myfirstapp;

import android.os.Bundle;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import Task.Task;
import Debug.Debug;
import FileManager.FileManager;

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
        PendingIntent pi = PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, task.getCalendar().getTimeInMillis(), pi);
        Toast.makeText(context, "Scheduled" + task.toString(), Toast.LENGTH_LONG).show();
    }
}
