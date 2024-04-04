package com.example.notification.firebase;

import androidx.annotation.NonNull;

import com.example.notification.utils.NotificationUtils;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessagingReciever extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        if (message.getNotification() != null){
                NotificationUtils.getInstance().showNotification(getApplicationContext(),
                        message.getNotification().getTitle(),
                        message.getNotification().getBody());
            }
        }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
    }
}

