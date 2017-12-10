package pl.edu.agh.kis.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static final String CHANNEL_ID = "main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();

        Button startForeground = findViewById(R.id.startForegroundButton);
        startForeground.setOnClickListener(view -> {
            Intent intent = new Intent(this, ForegroundService.class);
            intent.setAction(ForegroundService.STARTFOREGROUND_ACTION);
            startService(intent);
        });

        Button stopForeground = findViewById(R.id.stopForegroundButton);
        stopForeground.setOnClickListener(view -> {
            Intent intent = new Intent(this, ForegroundService.class);
            intent.setAction(ForegroundService.STOPFOREGROUND_ACTION);
            startService(intent);
        });
    }



    private void createNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            CharSequence name = "Main notification channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel =
                    new NotificationChannel(CHANNEL_ID, name, importance);

            mNotificationManager.createNotificationChannel(mChannel);
        }
    }
}
