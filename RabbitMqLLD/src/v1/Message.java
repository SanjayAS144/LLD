package v1;

import java.util.UUID;

public class Message {
    private String id;
    private final String message;
    private String messageType;
    public Message(String message, String messageType) {
        this.id = UUID.randomUUID().toString();
        this.message = message;
        this.messageType = messageType;
    }
    public Message(String message){
        this.message = message;
    }

    public String getId(){
        return this.id;
    }

    public String getMessage() {
        return message;
    }
    public String getMessageType() {
        return messageType;
    }
}
