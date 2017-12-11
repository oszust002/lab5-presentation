package pl.edu.agh.kis.broadcast_receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Random;

// NO CHANGES NEEDED IN THIS CLASS
public class RollDiceBroadcastReceiver extends BroadcastReceiver {
    private Random random = new Random();

    protected String getRolledString(Context context, int rolledValue) {
        return context.getResources().getString(R.string.dice_rolled_result, rolledValue);
    }

    protected int rollDice() {
        return random.nextInt(10);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int rolledValue = rollDice();
        Toast.makeText(context, getRolledString(context, rolledValue), Toast.LENGTH_SHORT).show();
    }
}
