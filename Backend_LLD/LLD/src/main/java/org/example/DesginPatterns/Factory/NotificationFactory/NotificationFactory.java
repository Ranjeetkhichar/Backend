package org.example.DesginPatterns.Factory.NotificationFactory;

public class NotificationFactory {
    public static Notification createNotification(NotificationType type, String recipient, String sender, String reciever){
        switch(type){
            case EMAIL:
                return new EmailNotification(recipient, sender, reciever);
            case SMS:
                return new SmsNotification(recipient, reciever);
            case PUSH:
                return new PushNotification(recipient, reciever);
        }
        return null;
    }
}
