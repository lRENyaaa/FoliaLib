package ltd.rymc.folialib.util.adapter;

import org.apache.logging.log4j.Logger;

public class Log4j2LoggerAdapter implements UniversalLogger {
    private final Logger logger;

    public Log4j2LoggerAdapter(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void info(String message) {
        logger.info(message);
    }

    @Override
    public void warn(String message) {
        logger.warn(message);
    }

    @Override
    public void error(String message) {
        logger.error(message);
    }

    @Override
    public void debug(String message) {
        logger.debug(message);
    }

    @Override
    public void trace(String message) {
        logger.trace(message);
    }

    @Override
    public void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }
}