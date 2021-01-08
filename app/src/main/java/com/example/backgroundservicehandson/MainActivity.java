package com.example.backgroundservicehandson;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         resultTextView = findViewById(R.id.text_view_result);

    }

    public void StartBackgroundService(View view) {
        Intent intent = new Intent(this, FirstBackgroundService.class);
        startService(intent);
    }

    public void StopBackgroundService(View view) {
        Intent intent = new Intent(this, FirstBackgroundService.class);
        stopService(intent);
    }

    public void startIntentService(View view) {
        Intent intent = new Intent(this, FirstIntentService.class);

        // that is the way to push data to the service.
        intent.putExtra("seconds", 3);
        startService(intent);
    }

    // register a broadcast receiver
    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("first.broadcast");
        LocalBroadcastManager.getInstance(this).registerReceiver(firstLocalBroadcastReceiver, intentFilter);
    }

    // the way to receive broadcasts from the service
    private BroadcastReceiver firstLocalBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int result = intent.getIntExtra("result", 8);
            resultTextView.setText(result + "");
        }
    };

    // unregister broadcast receiver
    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(firstLocalBroadcastReceiver);
    }
}