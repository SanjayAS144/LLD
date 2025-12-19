package v2;

import java.util.UUID;

public class Message {
    private String id;
    private final String message;
    private String routingKey;
    public Message(String message, String routingKey) {
        this.id = UUID.randomUUID().toString();
        this.message = message;
        this.routingKey = routingKey;
    }

    public String getId(){
        return this.id;
    }

    public String getMessage() {
        return message;
    }
    public String getRoutingKey() {
        return routingKey;
    }
}
