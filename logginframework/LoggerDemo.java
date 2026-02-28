package logginframework;

public class LoggerDemo {
    public void initializeLogger() {
        Logger logger = Logger.getInstance();
        logger.info("Info message is here");
        logger.debug("Info message is here");
        logger.warning("Info message is here");
        logger.error("Info message is here");
    }
}
