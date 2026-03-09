package pubsub.entities;

import java.util.UUID;

public class Message {
    public String id;
    public String content;

    public Message(String content) {
        this.id = UUID.randomUUID().toString();
        this.content = content;
    }
}
