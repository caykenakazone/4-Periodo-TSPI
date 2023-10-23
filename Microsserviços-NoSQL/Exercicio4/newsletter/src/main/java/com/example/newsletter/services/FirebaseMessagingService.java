package com.example.newsletter.services;

import com.example.newsletter.models.NotificationMessage;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FirebaseMessagingService {
    @Autowired
    private FirebaseMessaging firebaseMessaging;

    public ResponseEntity<?> sendNotification(NotificationMessage notificationMessage) {
        var notification = Notification
                .builder()
                .setTitle(notificationMessage.getTitle())
                .setBody(notificationMessage.getBody())
                .setImage(notificationMessage.getImage())
                .build();

        var message = Message
                .builder()
                .setToken(notificationMessage.getRecipientToken())
                .setNotification(notification)
                .putAllData(notificationMessage.getData())
                .build();
        try {
            firebaseMessaging.send(message);
            return ResponseEntity.ok().build();
        } catch (FirebaseMessagingException ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
