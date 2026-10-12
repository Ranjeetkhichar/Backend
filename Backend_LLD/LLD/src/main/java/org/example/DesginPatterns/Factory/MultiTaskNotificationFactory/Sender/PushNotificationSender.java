package org.example.DesginPatterns.Factory.MultiTaskNotificationFactory.Sender;

import org.example.DesginPatterns.Factory.MultiTaskNotificationFactory.Notification.Notification;
import org.example.DesginPatterns.Factory.MultiTaskNotificationFactory.NotificationType;

public class PushNotificationSender extends NotificationSender {
    public PushNotificationSender(Notification notification) {
        super(notification);
    }

    @Override
    public void send() {
        System.out.println("Sending Push notification to " + getNotification().getRecipient());
    }

    @Override
    public NotificationType notificationType() {
        return NotificationType.PUSH;
    }
}
