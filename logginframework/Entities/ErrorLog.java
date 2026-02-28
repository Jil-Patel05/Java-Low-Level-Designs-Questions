package logginframework.Entities;

import java.time.LocalDateTime;

import logginframework.GlobalEnums.LOG_LEVEL;
import logginframework.Observers.LogSubject;

public class ErrorLog extends LogHandler {
    public ErrorLog(LOG_LEVEL level) {
        this.level = level;
    }

    @Override
    public void publish(String message, LogSubject logSubject) {
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime.toString() + " " + "[ERROR] " + message);
        logSubject.notifyObservers(LOG_LEVEL.ERROR, message);
    }
}
