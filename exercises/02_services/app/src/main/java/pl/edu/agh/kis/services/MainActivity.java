package pl.edu.agh.kis.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String CHANNEL_ID = "main";

    private BoundService boundService = null;

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


        Button bindButton = findViewById(R.id.bindButton);
        bindButton.setOnClickListener(v -> {
            if(boundService == null) {
                Intent intent = new Intent(this, BoundService.class);
                bindService(intent, connection, BIND_AUTO_CREATE);
            }
        });

        Button randomButton = findViewById(R.id.randomButton);
        randomButton.setOnClickListener(v -> {
            if (boundService != null) {
                int number = boundService.getRandomNumber();
                Toast.makeText(this, "Random number: " + number, Toast.LENGTH_SHORT).show();
            }
        });
        Button unbindButton = findViewById(R.id.unbindButton);
        unbindButton.setOnClickListener(v -> {
            if (boundService != null) {
                unbindService(connection);
                boundService = null;
            }
        });
    }


    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BoundService.LocalBinder binder = (BoundService.LocalBinder) service;
            boundService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            boundService = null;
        }
    };



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
