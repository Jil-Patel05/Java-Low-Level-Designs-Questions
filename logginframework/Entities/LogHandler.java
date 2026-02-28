package logginframework.Entities;

import logginframework.GlobalEnums.LOG_LEVEL;
import logginframework.Observers.LogSubject;

public abstract class LogHandler {
    public LOG_LEVEL level;
    private LogHandler nextLogger;

    public void setNextLogger(LogHandler nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void logMessage(LOG_LEVEL level, String message, LogSubject logSubject) {
        try {
            if (this.level == level) {
                this.publish(message, logSubject);
                return;
            } else if (this.nextLogger != null) {
                this.nextLogger.logMessage(level, message, logSubject);
                return;
            }
            throw new Exception("Logger type is not present");

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public abstract void publish(String message, LogSubject logSubject);
}
