package com.example.hrminiapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class PowerBroadcastReceiver extends BroadcastReceiver {
    // Check intent and show message
    @Override
    public void onReceive(Context context, Intent intent) {
        if(Intent.ACTION_POWER_CONNECTED.equals(intent.getAction()))
        {
            Toast.makeText(context, "Power connected", Toast.LENGTH_LONG).show();
        }
    }
}
