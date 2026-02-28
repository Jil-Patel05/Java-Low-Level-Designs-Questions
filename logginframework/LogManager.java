package logginframework;

import logginframework.Entities.DebugLog;
import logginframework.Entities.ErrorLog;
import logginframework.Entities.InfoLog;
import logginframework.Entities.LogHandler;
import logginframework.Entities.WarningLog;
import logginframework.GlobalEnums.LOG_LEVEL;
import logginframework.Observers.DBObserver;
import logginframework.Observers.FileObserver;
import logginframework.Observers.LogSubject;

public class LogManager {
    public static LogHandler buildLogHandlers() {
        LogHandler infoLogger = new InfoLog(LOG_LEVEL.INFO);
        LogHandler debugLogger = new DebugLog(LOG_LEVEL.DEBUG);
        LogHandler warningLogger = new WarningLog(LOG_LEVEL.WARNING);
        LogHandler errorLogger = new ErrorLog(LOG_LEVEL.ERROR);

        infoLogger.setNextLogger(debugLogger);
        debugLogger.setNextLogger(warningLogger);
        warningLogger.setNextLogger(errorLogger);

        return infoLogger;
    }

    public static LogSubject buildLogSubject() {
        LogSubject subject = new LogSubject();
        subject.addObserver(LOG_LEVEL.INFO, new FileObserver());
        subject.addObserver(LOG_LEVEL.DEBUG, new DBObserver());
        return subject;
    }
}
