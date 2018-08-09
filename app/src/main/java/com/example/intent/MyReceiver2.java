package com.example.intent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import static com.example.intent.MyService.TAG;

public class MyReceiver2 extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive2: "+intent.getStringExtra("MainActivity"));
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
    }
}
