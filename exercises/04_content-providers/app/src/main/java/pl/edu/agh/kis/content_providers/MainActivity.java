package pl.edu.agh.kis.content_providers;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {
    Cursor contactsCursor;

    void loadContacts() {
        setContentView(R.layout.activity_main);

        /*
         * TODO #1 create projection with columns:
         * - ContactsContract.Contacts.DISPLAY_NAME
         * - ContactsContract.CommonDataKinds.Phone.NUMBER
         * - ContactsContract.Contacts._ID
         */
        String contactProjection[] = {
                //...
        };

        /*
         * TODO #2 run query returning cursor for given projection using URI:
         * ContactsContract.CommonDataKinds.Phone.CONTENT_URI
         */
        //contactsCursor = ...;

        // NO CHANGES NEEDED BEYOND THIS POINT
        if (contactsCursor == null || contactsCursor.getCount() == 0) {
            setContentView(R.layout.contact_list_empty);
            return;
        }

        int to[] = {
                R.id.name,
                R.id.phone
        };

        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this,
                R.layout.contact_item,
                contactsCursor,
                contactProjection,
                to,
                0);

        ListView contactList = findViewById(R.id.contacts_listview);
        contactList.setAdapter(simpleCursorAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (contactsCursor != null) contactsCursor.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (hasContactPermission()) loadContacts();
    }

    protected boolean hasContactPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) ==
                PackageManager.PERMISSION_GRANTED;
    }

    void requestContactPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_CONTACTS},
                0);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 0 && grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            loadContacts();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!hasContactPermission()) {
            setContentView(R.layout.no_permissions);
            requestContactPermission();
        }
    }
}
