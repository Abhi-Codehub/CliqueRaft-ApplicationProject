package com.example.cliqueraft_project;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.HashSet;

public class MainActivity extends AppCompatActivity {
    String text;
    Button notify;
    Button modal;
    public void completed(View view){
        EditText inputText =(EditText) findViewById(R.id.inputText);
        inputText.setText("COMPLETED");
    }

    public void toast(View view) {
        EditText inputText =(EditText) findViewById(R.id.inputText);
        text = inputText.getText().toString();

        if (TextUtils.isEmpty(inputText.getText().toString())) {
            Toast.makeText(MainActivity.this, "Empty field not allowed!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
        }
    }

    public void notify(View view){
        notify = findViewById(R.id.notifyButton);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("My Notification", "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            // Register the channel with the system; you can't change the importance or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //notification code goes here
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "My Notification")
                        .setSmallIcon(R.drawable.notification_icon)
                        .setContentTitle("Clique Raft")
                        .setContentText("Hello Abhishek From Clique Raft")
                        .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainActivity.this);
                managerCompat.notify(1,builder.build());
            }
        });
    }

    public void modal(View view){
        modal = findViewById(R.id.alertButton);
        modal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle(text)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "Button CLicked To YES ", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "Button CLicked To NO ", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}