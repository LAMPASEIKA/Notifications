package com.example.notification;

import android.Manifest;
import android.content.PeriodicSync;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ExistingWorkPolicy;
import androidx.work.Operation;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.example.notification.utils.MyWorker;
import com.example.notification.utils.NotificationUtils;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.ButtonPush);
        button.setOnClickListener(v -> {
            //askNotificationPermission();
            //NotificationUtils.getInstance().showNotification(getApplicationContext(), "SUPER", "MY_FIRST_NOTIFICATION");
            PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(MyWorker.class, 30, TimeUnit.SECONDS)
                    .build();
            WorkManager workManager = WorkManager.getInstance(getApplicationContext());
            Operation enqueue = workManager.enqueueUniquePeriodicWork("Notification", ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE, workRequest);

        });

        Button buttonStop = findViewById(R.id.ButtonStop);

        buttonStop.setOnClickListener(v -> {
            WorkManager.getInstance(getApplicationContext()).cancelAllWorkByTag("Notification");
        });

        System.out.println(NotificationUtils.getInstance().increaseA());

        System.out.println(NotificationUtils.getInstance().increaseA());

        System.out.println(NotificationUtils.getInstance().increaseA());

        NotificationUtils.getInstance().createNotificationChannel(getApplication());

    }

    private final ActivityResultLauncher<String> requestPermission =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted){
                    Toast.makeText(this, "Notification is ok", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "BAD BAD", Toast.LENGTH_SHORT).show();
                }
            });

    private void askNotificationPermission(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            requestPermission.launch(Manifest.permission.POST_NOTIFICATIONS);
        } else{
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED){
                System.out.println("urapobeda");
            }

        }

    }

}
