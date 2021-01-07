package com.example.backgroundservicehandson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void StartBackgroundService(View view) {
        Intent intent = new Intent(this, FirstBackgroundService.class);
        startService(intent);
    }

    public void StopBackgroundService(View view) {
        Intent intent = new Intent(this, FirstBackgroundService.class);
        stopService(intent);
    }
}