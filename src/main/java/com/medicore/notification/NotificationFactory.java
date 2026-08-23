package com.medicore.notification;

public class NotificationFactory {
    public static NotificationService createNotification(String type){
        if ("EMAIL".equalsIgnoreCase((type))
        ) {

            return new EmailNotificationService();
        }
        if ("SMS".equalsIgnoreCase((type))
        ) {

            return new SmsNotificationService();
        }

        throw new IllegalArgumentException("Unsupported notification type");
    }
}
