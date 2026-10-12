package org.example.DesginPatterns.Factory.MultiTaskNotificationFactory;

import org.example.DesginPatterns.Factory.MultiTaskNotificationFactory.Notification.Notification;
import org.example.DesginPatterns.Factory.MultiTaskNotificationFactory.Notification.PushNotification;
import org.example.DesginPatterns.Factory.MultiTaskNotificationFactory.Sender.NotificationSender;
import org.example.DesginPatterns.Factory.MultiTaskNotificationFactory.Sender.PushNotificationSender;
import org.example.DesginPatterns.Factory.MultiTaskNotificationFactory.Template.NotificationTemplate;
import org.example.DesginPatterns.Factory.MultiTaskNotificationFactory.Template.PushNotificationTemplate;

public class PushNotificationFactory extends NotificationFactory {

    @Override
    public NotificationType notificationType() {
        return NotificationType.PUSH;
    }

    @Override
    public Notification createNotification(String recipient, String sender, NotificationTemplate template) {
        return new PushNotification(recipient, template);
    }

    @Override
    public NotificationTemplate createNotificationTemplate(String message) {
        return new PushNotificationTemplate(message);
    }

    @Override
    public NotificationSender createNotificationSender(Notification notification) {
        return new PushNotificationSender(notification);
    }
}
