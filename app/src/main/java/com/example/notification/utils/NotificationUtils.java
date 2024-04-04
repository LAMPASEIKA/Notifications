package com.example.notification.utils;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.notification.MainActivity;
import com.example.notification.R;

public class NotificationUtils {

    private static final String CHANNEL_ID = "NOTIF";
    private static final String CHANNEL_NAME = "NOTIFNAME";
    private static NotificationUtils instance;

    NotificationManager notificationManager;

    public Integer a = 0;

    public Integer increaseA() {
        return ++a;
    }

    private NotificationUtils() {
    }

    public static NotificationUtils getInstance() {
        if (instance == null) {
            instance = new NotificationUtils();
        }
        return instance;
    }

    public void createNotificationChannel(Context context) {
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        notificationManager.createNotificationChannel(channel);
    }

    public void showNotification(Context context, String title, String description) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(Intent.EXTRA_TITLE, "BLABLA");
        intent.putExtra("text", "blabla");
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(description)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_EVENT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(false)
                .build();

        NotificationManagerCompat notificationManagerr = NotificationManagerCompat.from(context);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        notificationManagerr.notify(123, notification);
    }

}
