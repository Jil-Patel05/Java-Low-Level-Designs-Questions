package pubsub;

import pubsub.GlobalEnums.TOPICS;
import pubsub.entities.Message;
import pubsub.entities.Subscriber;

public class PubSubDemo {
    public void initializePubSub() {
        Message msg1 = new Message("First");
        Message msg2 = new Message("Second");

        PubSubService pb = PubSubService.getInstance();
        pb.createTopic(TOPICS.STOCK_IN);
        pb.createTopic(TOPICS.STOCK_OUT);

        pb.subscribeToTopic(TOPICS.STOCK_IN, new Subscriber());
        pb.subscribeToTopic(TOPICS.STOCK_IN, new Subscriber());
        pb.subscribeToTopic(TOPICS.STOCK_OUT, new Subscriber());
        pb.subscribeToTopic(TOPICS.STOCK_OUT, new Subscriber());

        pb.publishMessage(TOPICS.STOCK_IN, msg1);
        System.out.println("Flow is here as well");
        // pb.shutdown();
    }
}
