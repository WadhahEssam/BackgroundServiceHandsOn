package com.example.backgroundservicehandson;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class FirstBackgroundService extends Service {

    private static final String TAG = FirstBackgroundService.class.getSimpleName();

    // executed whenever the service is instantiated
    // this method can be omitted
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "on Create");
    }

    // executed whenever the service is started
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "on Start");
        return super.onStartCommand(intent, flags, startId);
    }

    // dah
    @Override
    public void onDestroy() {
        Log.i(TAG, "on Destroy");
        super.onDestroy();
    }

    // this is the most important method, this always needs to return null, you don't have to change it.
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "on Bind");
        return null;
    }
}
