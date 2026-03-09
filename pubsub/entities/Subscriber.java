package pubsub.entities;

public class Subscriber {
    public Subscriber() {

    }

    public void getMessage(Message message) {
        System.out.println("Received message");
    }
}
