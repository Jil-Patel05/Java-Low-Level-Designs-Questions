package logginframework.Entities;

import java.time.LocalDateTime;

import logginframework.GlobalEnums.LOG_LEVEL;
import logginframework.Observers.LogSubject;

public class InfoLog extends LogHandler {

    public InfoLog(LOG_LEVEL level) {
        this.level = level;
    }

    @Override
    public void publish(String message, LogSubject logSubject) {
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime.toString() + " " + "[INFO] " + message);
        logSubject.notifyObservers(LOG_LEVEL.INFO, message);
    }

}
