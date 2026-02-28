package logginframework.Entities;

import java.time.LocalDateTime;

import logginframework.GlobalEnums.LOG_LEVEL;
import logginframework.Observers.LogSubject;

public class DebugLog extends LogHandler {
    public DebugLog(LOG_LEVEL level) {
        this.level = level;
    }

    @Override
    public void publish(String message, LogSubject logSubject) {
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime.toString() + " " + "[DEBUG] " + message);
        logSubject.notifyObservers(LOG_LEVEL.DEBUG, message);
    }
}
