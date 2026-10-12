package org.example.DesginPatterns.Factory.NotificationFactory;

import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class NotificationTest {

    @Test
    public void testParentClass() {
        // Define the package where your processors are located
        String packageName = NotificationTest.class.getPackageName();

        // Get all subtypes of Notification using reflection
        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        Set<Class<? extends Notification>> subTypes = reflections.getSubTypesOf(Notification.class);

        // Ensure there is at least one subclass
        assertEquals(3, subTypes.size(),
                "If the factory is implemented correctly, there should be 3 concrete product classes of Notification.");

        // Check if Notification has the required methods and fields
        int stringCount = Arrays.stream(Notification.class.getDeclaredFields())
                .filter(field -> field.getType().equals(String.class))
                .toArray().length;
        assertEquals(2, stringCount,
                "If the parent class is implemented correctly, it should have 2 String fields for the common fields of message and recipient.");

        boolean hasSupportsType = Arrays.stream(Notification.class.getDeclaredMethods())
                .anyMatch(method -> method.getName().equals("notificationType")
                        && method.getParameterCount() == 0
                        && method.getReturnType().equals(NotificationType.class)
                        && Modifier.isAbstract(method.getModifiers()));
        assertTrue(hasSupportsType,
                "If the parent class is implemented correctly, it should have a method called supportsType with no parameters and NotificationType return type.");

        boolean hasSendNotification = Arrays.stream(Notification.class.getDeclaredMethods())
                .anyMatch(method -> method.getName().equals("sendNotification")
                        && method.getParameterCount() == 0
                        && method.getReturnType().equals(void.class)
                        && Modifier.isAbstract(method.getModifiers()));
        assertTrue(hasSendNotification,
                "If the parent class is implemented correctly, it should have a method called sendNotification with no parameters and void return type.");

    }

    @Test
    public void testNotificationFactory() {

        // Check if the NotificationFactory class has a static method to create the
        // document processor
        boolean hasCreateMethod = Arrays.stream(NotificationFactory.class.getDeclaredMethods())
                .anyMatch(method -> Modifier.isStatic(method.getModifiers())
                        && method.getReturnType().equals(Notification.class)
                        && method.getParameterCount() == 4
                        && Arrays.asList(method.getParameterTypes())
                        .contains(NotificationType.class)
                        && Arrays.asList(method.getParameterTypes()).contains(String.class));

        assertTrue(hasCreateMethod,
                "If the factory is implemented correctly, it should have a static method to create a notification that takes four parameters: NotificationType, String message, String recipient, and String sender.");
    }

    @Test
    public void testDocumentNotificationFactoryCreateMethodInvocation() {
        // Get all methods in NotificationFactory
        Method[] methods = NotificationFactory.class.getDeclaredMethods();

        // Check if any method is a static method that returns Notification
        Method createMethod = Arrays.stream(methods)
                .filter(method -> Modifier.isStatic(method.getModifiers())
                        && method.getReturnType().equals(Notification.class)
                        && method.getParameterCount() == 4
                        && Arrays.asList(method.getParameterTypes())
                        .contains(NotificationType.class)
                        && Arrays.asList(method.getParameterTypes()).contains(String.class))
                .findFirst()
                .orElse(null);

        assertNotNull(createMethod,
                "If the factory is implemented correctly, it should have a static method to create a notification that takes four parameters: NotificationType, String message, String recipient, and String sender.");

        // Create a NotificationType and String to invoke the create method
        NotificationType notificationType = NotificationType.EMAIL;
        String sender = "sender";
        String recipient = "recipient";
        String message = "message";

        // Invoke the create method with parameters in different orders
        Notification processor = null;

        try {
            processor = (Notification) createMethod.invoke(null, notificationType, message, recipient,
                    sender);
        } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
            fail("If the factory is implemented correctly, the create method should be able to be invoked with the following parameters: NotificationType, String message, String recipient, and String sender.");
        }

        // Verify that the returned processors are not null and match the expected type
        assertNotNull(processor,
                "If the factory is implemented correctly, the create method should return a non-null Notification.");
        assertEquals(notificationType, processor.notificationType(),
                "If the factory is implemented correctly, the create method should return a Notification with the correct type.");

    }

}