package pl.edu.agh.kis.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

/**
 * Created by maxmati on 12/10/17.
 */

public class ForegroundService extends Service {

    public static String STARTFOREGROUND_ACTION = "pl.edu.agh.kis.services.startforeground";
    public static String MAIN_ACTION = "pl.edu.agh.kis.services.main";
    public static String STOPFOREGROUND_ACTION = "pl.edu.agh.kis.services.stopforeground";


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (STARTFOREGROUND_ACTION.equals(intent.getAction())) {
            Intent notificationIntent = new Intent(this, MainActivity.class);
            notificationIntent.setAction(MAIN_ACTION);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                    notificationIntent, 0);

            Intent stopIntent = new Intent(this, ForegroundService.class);
            stopIntent.setAction(STOPFOREGROUND_ACTION);
            PendingIntent pStopIntent = PendingIntent.getService(this, 0,
                    stopIntent, 0);

            Bitmap icon = BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_more);

            Notification notification = new NotificationCompat.Builder(this, "main")
                    .setContentTitle("Sample Foreground Service")
                    .setTicker("Sample Foreground Service")
                    .setContentText("Running service")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setLargeIcon(Bitmap.createScaledBitmap(icon, 128, 128, false))
                    .setContentIntent(pendingIntent)
                    .setOngoing(true)
                    .addAction(android.R.drawable.ic_menu_delete, "Stop",
                            pStopIntent).build();
            startForeground(101, notification);
        } else if (intent.getAction().equals(STOPFOREGROUND_ACTION)) {
            stopForeground(true);
            stopSelf();
        }
        return START_STICKY;

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
