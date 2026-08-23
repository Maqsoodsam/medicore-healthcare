package com.medicore.notification;

public class EmailNotificationService implements NotificationService{

    @Override
    public void sendNotification(String message) {
        System.out.println("Email" +message);

    }
}
