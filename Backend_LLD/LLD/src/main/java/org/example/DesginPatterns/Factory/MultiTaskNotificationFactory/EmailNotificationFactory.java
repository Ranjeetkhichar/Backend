package org.example.DesginPatterns.Factory.MultiTaskNotificationFactory;

import org.example.DesginPatterns.Factory.MultiTaskNotificationFactory.Notification.EmailNotification;
import org.example.DesginPatterns.Factory.MultiTaskNotificationFactory.Notification.Notification;
import org.example.DesginPatterns.Factory.MultiTaskNotificationFactory.Sender.EmailNotificationSender;
import org.example.DesginPatterns.Factory.MultiTaskNotificationFactory.Sender.NotificationSender;
import org.example.DesginPatterns.Factory.MultiTaskNotificationFactory.Template.EmailNotificationTemplate;
import org.example.DesginPatterns.Factory.MultiTaskNotificationFactory.Template.NotificationTemplate;

public class EmailNotificationFactory extends NotificationFactory {

    @Override
    public NotificationType notificationType() {
        return NotificationType.EMAIL;
    }

    @Override
    public Notification createNotification(String recipient, String sender, NotificationTemplate template) {
        return new EmailNotification(recipient, sender, template);
    }

    @Override
    public NotificationTemplate createNotificationTemplate(String message) {
        return new EmailNotificationTemplate(message);
    }

    @Override
    public NotificationSender createNotificationSender(Notification notification) {
        return new EmailNotificationSender(notification);
    }

}
