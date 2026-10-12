package org.example.DesginPatterns.Builder.HW;



@WithBuilder
public class MessageBuilder {

    private final MessageType messageType;
    private final String content;
    private final String sender;
    private final String recipient;
    private final boolean isDelivered;
    private final long timestamp;

    private MessageBuilder(Builder builder) {
        this.messageType = builder.messageType;
        this.content = builder.content;
        this.sender = builder.sender;
        this.recipient = builder.recipient;
        this.isDelivered = builder.isDelivered;
        this.timestamp = builder.timestamp;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public static Builder build() {
        return new Builder();
    }


    public static class Builder {
        private MessageType messageType;
        private String content;
        private String sender;
        private String recipient;
        private boolean isDelivered;
        private long timestamp;

        private Builder(){}

        public MessageBuilder build() {
            return new MessageBuilder(this);
        }

        public String getContent() {
            return content;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public String getSender() {
            return sender;
        }

        public Builder setSender(String sender) {
            this.sender = sender;
            return this;
        }

        public String getRecipient() {
            return recipient;
        }

        public Builder setRecipient(String recipient) {
            this.recipient = recipient;
            return this;
        }

        public boolean isDelivered() {
            return isDelivered;
        }

        public Builder setDelivered(boolean delivered) {
            isDelivered = delivered;
            return this;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public Builder setTimestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public MessageType getMessageType() {
            return messageType;
        }

        public Builder setMessageType(MessageType messageType) {
            this.messageType = messageType;
            return this;
        }

    }

}