package pubsub.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import pubsub.GlobalEnums.TOPICS;

public class Topic {
    private String id;
    private TOPICS topicName;
    private List<Subscriber> subscribers = new ArrayList<>();
    private ExecutorService service;

    public Topic(String id, TOPICS topicName, ExecutorService service) {
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
            System.out.println(sb.hashCode());
            this.service.submit(() -> {
                sb.getMessage(msg);
            });
        }
    }
}
