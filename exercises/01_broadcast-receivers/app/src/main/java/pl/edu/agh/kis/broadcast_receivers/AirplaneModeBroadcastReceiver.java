package pl.edu.agh.kis.broadcast_receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AirplaneModeBroadcastReceiver extends BroadcastReceiver {
    public static final String TAG = "AirplaneModeBrReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        MainActivity activity = (MainActivity) context;
        boolean isAirplaneModeOn = intent.getBooleanExtra("state", false);
        Log.d(TAG, String.format("Settings state to: %s", isAirplaneModeOn));
        activity.updateAirplaneModeText(isAirplaneModeOn);
    }
}
