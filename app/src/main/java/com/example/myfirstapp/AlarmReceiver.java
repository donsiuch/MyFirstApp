package com.example.myfirstapp;

import android.os.Bundle;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import Debug.Debug;

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


}
