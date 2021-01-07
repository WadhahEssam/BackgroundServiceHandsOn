package com.example.backgroundservicehandson;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class FirstBackgroundService extends Service {

    private static final String TAG = FirstBackgroundService.class.getSimpleName();

    // executed whenever the service is instantiated
    // this method can be omitted
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "on Create, Thread " + Thread.currentThread().getName());
    }

    // executed whenever the service is started
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "on Start, Thread " + Thread.currentThread().getName());
        new FirstAsyncTask().execute();

        // Here you should perform the task
//        try {
//            Thread.sleep(12000); // sleep for 12 seconds
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        // this will allow the background service to be restarted after the service is killed by the operating system.
        // you can also use START_REDELIVER_INTENT if you want to pass data throw the intent.
        return START_STICKY;
    }

    // dah
    @Override
    public void onDestroy() {
        Log.i(TAG, "on Destroy, Thread " + Thread.currentThread().getName());
        super.onDestroy();
    }

    // this is the most important method, this always needs to return null, you don't have to change it.
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "on Bind, Thread " + Thread.currentThread().getName());
        return null;
    }


    // this will allow you to create a background/worker thread where you can run the bacground service in.
    class FirstAsyncTask extends AsyncTask {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i(TAG, "onPreExecute, Thread " + Thread.currentThread().getName());
        }

        @Override
        // this function will perform the operation in the background.
        // but this function has no access to the ui thread to display any ui thing
        // ths is the only function that doesn't run in the ui thread from all the AsyncTask functions
        protected Object doInBackground(Object[] objects) {
            Log.i(TAG, "doInBackground, Thread " + Thread.currentThread().getName());

            // Here you should perform the task (like uploading a file or playing the music, or other stuff)
            int counter = 1;
            while (counter <= 6) {
                publishProgress("Time elapsed : " + counter + " seconds");
                try {
                    Thread.sleep(1000); // sleep for 12 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                counter++;
            }

            return null;
        }

        // this function has access to the ui thread so you can do things that will interact with the user (it will run in the ui thread)
        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
            Log.i(TAG, "onProgressUpdate, Counter:" + values[0]+  ", Thread " + Thread.currentThread().getName());

            // if you try to show the toast in the doInBackground function, it will simply not work
            // one important note, is that you can never access the ui elements from anywhere inside the background service
            Toast.makeText(FirstBackgroundService.this, values[0].toString(), Toast.LENGTH_SHORT).show();;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            Log.i(TAG, "onPostExecute, Thread " + Thread.currentThread().getName());

            // this will stop the service after the background task is finished performing its stuff
            stopSelf();
        }
    }
}
