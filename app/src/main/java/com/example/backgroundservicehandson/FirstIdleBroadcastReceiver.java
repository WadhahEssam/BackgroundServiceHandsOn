package com.example.backgroundservicehandson;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class FirstIdleBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Device booted", Toast.LENGTH_SHORT).show();

        // This code should be executed when the device is successfully booted
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Intent i = new Intent(context, FirstJobIntentService.class);
            i.putExtra("seconds", 12);
            FirstJobIntentService.enqueueWork(context, i);
        }
    }
}
