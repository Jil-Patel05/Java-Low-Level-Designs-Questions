package logginframework;

import logginframework.Entities.LogHandler;
import logginframework.GlobalEnums.LOG_LEVEL;
import logginframework.Observers.LogSubject;

public class Logger {
    public static volatile Logger instance;
    private final LogHandler logHandler = LogManager.buildLogHandlers();
    private final LogSubject subject = LogManager.buildLogSubject();

    private Logger() {

    }

    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public void info(String message) {
        logHandler.logMessage(LOG_LEVEL.INFO, message, subject);
    }

    public void debug(String message) {
        logHandler.logMessage(LOG_LEVEL.DEBUG, message, subject);
    }

    public void warning(String message) {
        logHandler.logMessage(LOG_LEVEL.WARNING, message, subject);
    }

    public void error(String message) {
        logHandler.logMessage(LOG_LEVEL.ERROR, message, subject);
    }
}
