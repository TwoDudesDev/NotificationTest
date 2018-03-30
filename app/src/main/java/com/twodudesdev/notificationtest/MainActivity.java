package com.twodudesdev.notificationtest;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final Button iLikeToast = findViewById(R.id.makeToast);
        iLikeToast.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText filledInText = findViewById(R.id.notificationText);
                String notificationString = filledInText.getText().toString();
                Toast.makeText(MainActivity.this, notificationString + " Android Version: " + Build.VERSION.SDK_INT, Toast.LENGTH_SHORT).show();
            }
        });

        final Button topShelfNotification = findViewById(R.id.makeTray);
        final int[] topShelfNotificationId = {10};
        topShelfNotification.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText filledInText = findViewById(R.id.notificationText);
                String notificationTextTitle = "Button Clicked!";
                String notificationString = filledInText.getText().toString();
                String shelfChannelId = "shelfChannel";

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    CharSequence name = getString(R.string.channel_name);
                    String description = getString(R.string.channel_description);
                    int importance = NotificationManager.IMPORTANCE_DEFAULT;
                    NotificationChannel mChannel = new NotificationChannel(shelfChannelId, name, importance);
                    mChannel.setDescription(description);
                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    notificationManager.createNotificationChannel(mChannel);
                }


                NotificationCompat.Builder topShelfNotification = new NotificationCompat.Builder(MainActivity.this, shelfChannelId)
                        .setSmallIcon(R.drawable.top_shelf_image)
                        .setContentTitle(topShelfNotificationId[0] + " " +  notificationTextTitle)
                        .setContentText(notificationString)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(
                                "Sed ut perspiciatis, unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam eaque ipsa, quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt, explicabo."
                        ));

                NotificationManagerCompat topShelfManager = NotificationManagerCompat.from(MainActivity.this);
                topShelfManager.notify(topShelfNotificationId[0], topShelfNotification.build());
                topShelfNotificationId[0] = topShelfNotificationId[0] + 10;

            }
        });

    }
}
