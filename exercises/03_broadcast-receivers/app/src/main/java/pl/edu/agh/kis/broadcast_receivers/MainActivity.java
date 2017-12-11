package pl.edu.agh.kis.broadcast_receivers;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String ACTION_ROLL_DICE =
            "pl.edu.agh.kis.broadcast_receivers.ACTION_ROLL_DICE";

    BroadcastReceiver airplaneModeBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        airplaneModeBroadcastReceiver = new AirplaneModeBroadcastReceiver();
        IntentFilter airplaneModeIntentFilter =
                new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(airplaneModeBroadcastReceiver, airplaneModeIntentFilter);
    }

    public void broadcastRollDice(View view) {
        Intent rollActionIntent = new Intent();
        rollActionIntent.setAction(ACTION_ROLL_DICE);
        sendBroadcast(rollActionIntent);
    }

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
