package logginframework.Observers;

public class DBObserver implements Observer {
    @Override
    public void logMessage(String message) {
        System.out.println("Message is handled in DB observer");
    }
}
