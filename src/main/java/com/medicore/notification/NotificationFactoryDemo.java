package com.medicore.notification;

public class NotificationFactoryDemo {

    public static void main(String[] args)
    {
        NotificationService emailNotification = NotificationFactory.createNotification("EMAIL");
        emailNotification.sendNotification( "Your appointment has been created.");
        NotificationService smsNotification = NotificationFactory.createNotification("SMS");
        smsNotification.sendNotification( "Your appointment has been updated."); }
}

