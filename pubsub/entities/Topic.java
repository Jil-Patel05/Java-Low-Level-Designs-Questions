package pubsub.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class Topic {
    private String id;
    private String topicName;
    private List<Subscriber> subscribers = new ArrayList<>();
    private ExecutorService service;

    public Topic(String id, String topicName, ExecutorService service) {
        this.id = id;
        this.topicName = topicName;
        this.service = service;
    }

    public void addSubscriber(Subscriber subscriber) {
        this.subscribers.add(subscriber);
    }

    public void removeSubscriber(Subscriber subscriber) {
        this.subscribers.remove(subscriber);
    }

    public void brodCast(Message msg) {
        for (Subscriber sb : subscribers) {
            this.service.submit(() -> {
                sb.getMessage(msg);
            });
        }
    }
}
