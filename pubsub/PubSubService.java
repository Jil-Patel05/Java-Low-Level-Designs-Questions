package pubsub;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import pubsub.GlobalEnums.TOPICS;
import pubsub.entities.Message;
import pubsub.entities.Subscriber;
import pubsub.entities.Topic;

public class PubSubService {
    private static volatile PubSubService instance;
    private Map<TOPICS, Topic> topics = new HashMap<>();
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

    public void createTopic(TOPICS topicName) {
        String id = "1";
        Topic topic = new Topic(id, topicName, service);
        topics.put(topicName, topic);
    }

    public void subscribeToTopic(TOPICS topicName, Subscriber sub) {
        Topic topic = topics.get(topicName);
        topic.addSubscriber(sub);
    }

    public void unsubscribeToTopic(TOPICS topicName, Subscriber sub) {
        Topic topic = topics.get(topicName);
        topic.removeSubscriber(sub);
    }

    public void publishMessage(TOPICS topicName, Message msg) {
        Topic topic = topics.get(topicName);
        topic.brodCast(msg);
    }

    public void shutdown() {
        this.service.shutdown();
        try {
            if (!this.service.awaitTermination(60, TimeUnit.SECONDS)) {
                this.service.shutdownNow();
            }
            // In java When thread is interrupted it's interrupt signal become true
        } catch (InterruptedException ex) {
            System.out.println("Exception occur");
            this.service.shutdownNow();
            Thread.currentThread().interrupt(); // InterruptedException this exception will make thread intruupt signal
                                                // flag false, for high level
            // code who calls this shutdown method never know thread is interrupted so we
            // use this Trick for it.
        }
    }
}
