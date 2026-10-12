package org.example.DesginPatterns.Factory.MultiTaskNotificationFactory.Sender;

import org.example.DesginPatterns.Factory.MultiTaskNotificationFactory.*;
import org.example.DesginPatterns.Factory.MultiTaskNotificationFactory.Notification.Notification;


public abstract class NotificationSender {
    private final Notification notification;

    public NotificationSender(Notification notification) {
        this.notification = notification;
    }

    public Notification getNotification() {
        return notification;
    }

    public abstract void send();

    public abstract NotificationType notificationType();
}
