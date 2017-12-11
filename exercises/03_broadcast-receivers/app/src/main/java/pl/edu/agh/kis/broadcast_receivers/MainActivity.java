package pl.edu.agh.kis.broadcast_receivers;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO #1 create airplane mode receiver
        //airplaneModeBroadcastReceiver = ...;

        // TODO #2 create intent filter with action Intent.ACTION_AIRPLANE_MODE_CHANGED
        //IntentFilter airplaneModeIntentFilter = ...;

        // TODO #3 register broadcast receiver with intent filter
        //...
    }

    public void broadcastRollDice(View view) {
        // TODO #4 create new roll dice intent
        //Intent rollActionIntent = ..;

        // TODO #5 set action to ACTION_ROLL_DICE
        //...

        // TODO #6 send broadcast with newly created intent
        //...

        // TODO #7 register <receiver...> in AndroidManifest.xml
    }

    // NO CHANGES NEEDED AFTER THIS LINE
    public static final String ACTION_ROLL_DICE =
            "pl.edu.agh.kis.broadcast_receivers.ACTION_ROLL_DICE";

    BroadcastReceiver airplaneModeBroadcastReceiver;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(airplaneModeBroadcastReceiver);
    }

    public void updateAirplaneModeText(Boolean airplane) {
        TextView statusTextView = findViewById(R.id.airplane_mode_status);
        statusTextView.setText(airplane ? R.string.airplane_mode_on : R.string.airplane_mode_off);
    }
}
