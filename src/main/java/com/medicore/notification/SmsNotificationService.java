package com.medicore.notification;

public class SmsNotificationService implements NotificationService{

    @Override
    public void sendNotification(String message){
        System.out.println("SMS:" +message);
    }
}
