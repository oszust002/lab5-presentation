package pl.edu.agh.kis.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by maxmati on 12/10/17.
 */

public class BoundService extends Service {
    //TODO #4: add service to manifest

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null; //TODO #2: return own binder implementation (#1)
    }

    //TODO #1: Implement own class extending Binder class

    //TODO #3: Add method for generating random numbers
}
