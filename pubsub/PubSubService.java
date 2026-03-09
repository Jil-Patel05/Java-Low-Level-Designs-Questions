package pubsub;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import pubsub.entities.Message;
import pubsub.entities.Subscriber;
import pubsub.entities.Topic;

public class PubSubService {
    private static volatile PubSubService instance;
    private Map<String, Topic> topics = new HashMap<>();
    private ExecutorService service;

    private PubSubService() {
        this.service = Executors.newCachedThreadPool();
    }

    public PubSubService getInstance() {
        if (instance == null) {
            synchronized (PubSubService.class) {
                if (instance == null) {
                    instance = new PubSubService();
                }
            }
        }
        return instance;
    }

    public void createTopic(String topicName) {
        String id = "1";
        Topic topic = new Topic(id, topicName, service);
        topics.put(topicName, topic);
    }

    public void subscribeToTopic(String id, Subscriber sub) {
        Topic topic = topics.get(id);
        topic.addSubscriber(sub);
    }

    public void unsubscribeToTopic(String id, Subscriber sub) {
        Topic topic = topics.get(id);
        topic.removeSubscriber(sub);
    }

    public void publishMessage(String id, Message msg) {
        Topic topic = topics.get(id);
        topic.brodCast(msg);
    }
}
