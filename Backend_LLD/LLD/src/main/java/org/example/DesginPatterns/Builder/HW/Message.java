package org.example.DesginPatterns.Builder.HW;

public class Message {

    private MessageType messageType;
    private String content;
    private String sender;
    private String recipient;
    private boolean isDelivered;
    private long timestamp;

    public Message(MessageType messageType, String content, String sender, String recipient, boolean isDelivered, long timestamp) {
        this.messageType = messageType;
        this.content = content;
        this.sender = sender;
        this.recipient = recipient;
        this.isDelivered = isDelivered;
        this.timestamp = timestamp;
    }


    public Message(MessageBuilder.Builder builder) {
        this.messageType = builder.getMessageType(); // or builder.messageType if accessible
        this.content = builder.getContent();
        this.sender = builder.getSender();
        this.recipient = builder.getRecipient();
        this.isDelivered = builder.isDelivered();
        this.timestamp = builder.getTimestamp();
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public String getContent() {
        return content;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public long getTimestamp() {
        return timestamp;
    }
}