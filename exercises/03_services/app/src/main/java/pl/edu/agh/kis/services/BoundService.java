package pl.edu.agh.kis.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by maxmati on 12/10/17.
 */

public class BoundService extends Service {
    private final IBinder binder = new LocalBinder();
    private final Random mGenerator = new Random();


    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Created bound service", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Destroyed bound service", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }


    public class LocalBinder extends Binder {
        BoundService getService() {
            return BoundService.this;
        }
    }
}
