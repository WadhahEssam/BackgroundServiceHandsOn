package com.example.backgroundservicehandson;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

public class FirstJobIntentService extends JobIntentService {

    private static final String TAG = FirstJobIntentService.class.getSimpleName();


    // exposing the enqueue method
    public static void enqueueWork(Context context, Intent intent) {
        enqueueWork(context, FirstJobIntentService.class, 10, intent);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Intent service started", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "on Create, Thread " + Thread.currentThread().getName());
    }

    // works the same as onHandleIntent in the ServiceIntent that we used before.
    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        // notice that the thread here will not be the ui thread
        Log.i(TAG, "on Handle Work, Thread " + Thread.currentThread().getName());

        // receiving the data from the intentService, and same way can be used for the startedService.
        int sleepTime = intent.getIntExtra("seconds", 6);

        // Here you should perform the task (like uploading a file or playing the music, or other stuff)
        int counter = 1;
        while (counter <= sleepTime) {
            Log.i(TAG, "Time elapsed : " + counter + " seconds");
            try {
                Thread.sleep(1000); // sleep for 12 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter++;
        }
    }

    // this method will automatically be called after the work is done
    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Intent service stopped", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "on Destroy, Thread " + Thread.currentThread().getName());
    }
}
