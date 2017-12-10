package pl.edu.agh.kis.broadcast_receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

// NO CHANGES NEEDED IN THIS CLASS
public class AirplaneModeBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        MainActivity activity = (MainActivity) context;
        boolean isAirplaneModeOn = intent.getBooleanExtra("state", false);
        activity.updateAirplaneModeText(isAirplaneModeOn);
    }
}
