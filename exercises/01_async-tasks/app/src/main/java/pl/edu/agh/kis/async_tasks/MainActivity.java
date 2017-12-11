package pl.edu.agh.kis.async_tasks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView avatar = findViewById(R.id.avatar);

        final EditText emailView = findViewById(R.id.emailField);

        Button downloadButton = findViewById(R.id.downloadButton);

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: create and execute async task.
                //You can use `ImageFetcher.fetchAvatar` to fetch gravatar
                new DownloadAvatarTask(avatar).execute(emailView.getText().toString());
            }
        });



    }
}
