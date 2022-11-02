package com.voidsoul.longruntest;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

public class LongRunningService extends Service {
    private static final String TAG = "LongRunningService";
    public LongRunningService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Intent i = new Intent(this, LongRunningService.class);
        PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        manager.cancel(pi);
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: ");
            }
        }).start();
        Log.d(TAG, "onStartCommand: ");
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int ten_seconds = 3 * 1000;
        long triggerAtTime = SystemClock.elapsedRealtime() + ten_seconds;
        Intent i = new Intent(this, LongRunningService.class);
        PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            manager.setAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
            manager.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        }else{
            //manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
            manager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}