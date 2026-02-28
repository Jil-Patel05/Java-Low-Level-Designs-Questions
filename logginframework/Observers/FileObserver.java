package logginframework.Observers;

public class FileObserver implements Observer {

    @Override
    public void logMessage(String message) {
        System.out.println("Message is handled in File observer");
    }
}
