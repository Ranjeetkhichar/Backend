package org.example.DesginPatterns.Factory.MultiTaskNotificationFactory.Notification;

import org.example.DesginPatterns.Factory.MultiTaskNotificationFactory.NotificationType;
import org.example.DesginPatterns.Factory.MultiTaskNotificationFactory.Template.NotificationTemplate;

public class PushNotification extends Notification {
    public PushNotification(String recipient, NotificationTemplate template) {
        super(recipient, template);
    }

    public void sendNotification() {
        // Logic to send a push notification
        System.out.println("Push notification sent to device " + getRecipient());
        System.out.println("Message: " + getTemplate().getMessage());
    }

    public NotificationType notificationType() {
        return NotificationType.PUSH;
    }
}
