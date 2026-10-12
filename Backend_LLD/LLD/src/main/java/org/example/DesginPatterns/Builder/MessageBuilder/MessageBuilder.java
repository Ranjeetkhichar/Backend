package org.example.DesginPatterns.Builder.MessageBuilder;



@WithBuilder
public class MessageBuilder {

    private MessageType messageType;
    private String content;
    private String sender;
    private String recipient;
    private boolean isDelivered;
    private long timestamp;

    private MessageBuilder() {}

    public static MessageBuilder builder() {
        return new MessageBuilder();
    }

    public Message build() {
        return new Message(this);
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public MessageBuilder setMessageType(MessageType messageType) {
        this.messageType = messageType;
        return this;
    }

    public String getContent() {
        return content;
    }

    public MessageBuilder setContent(String content) {
        this.content = content;
        return this;
    }

    public String getSender() {
        return sender;
    }

    public MessageBuilder setSender(String sender) {
        this.sender = sender;
        return this;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public MessageBuilder setDelivered(boolean delivered) {
        isDelivered = delivered;
        return this;
    }

    public String getRecipient() {
        return recipient;
    }

    public MessageBuilder setRecipient(String recipient) {
        this.recipient = recipient;
        return this;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public MessageBuilder setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

}