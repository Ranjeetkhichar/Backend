package org.example.DesginPatterns.Factory.MultiTaskNotificationFactory;

import org.example.DesginPatterns.Factory.MultiTaskNotificationFactory.Notification.Notification;
import org.example.DesginPatterns.Factory.MultiTaskNotificationFactory.Sender.NotificationSender;
import org.example.DesginPatterns.Factory.MultiTaskNotificationFactory.Template.NotificationTemplate;

public abstract class NotificationFactory {
    public abstract NotificationType notificationType();
    public abstract Notification createNotification(String recipient, String sender, NotificationTemplate template);
    public abstract NotificationTemplate createNotificationTemplate(String message);
    public abstract NotificationSender createNotificationSender(Notification notification);

}
