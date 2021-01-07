package com.example.backgroundservicehandson;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

public class FirstIntentService extends IntentService {
    private static final String TAG = FirstIntentService.class.getSimpleName();

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public FirstIntentService() {
        super("FirstIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "on Create, Thread " + Thread.currentThread().getName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        // notice that the thread here will not be the ui thread
        Log.i(TAG, "on Handle Intent, Thread " + Thread.currentThread().getName());

        // Here you should perform the task (like uploading a file or playing the music, or other stuff)
        int counter = 1;
        while (counter <= 6) {
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
        Log.i(TAG, "on Destroy, Thread " + Thread.currentThread().getName());
    }
}
