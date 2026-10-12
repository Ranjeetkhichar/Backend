package org.example.DesginPatterns.Builder.HW;

public class Client {
    public static void main(String[] args) {

        Message message = Message.builder()
                .setMessageType(MessageType.AUDIO)
                .setContent("Ranjeet")
                .setSender("Pradeep")
                .build();

        System.out.println(message);

    }
}
