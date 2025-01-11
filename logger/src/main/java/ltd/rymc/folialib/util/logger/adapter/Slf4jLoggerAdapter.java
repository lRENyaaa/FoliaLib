package ltd.rymc.folialib.util.logger.adapter;

import org.slf4j.Logger;

public class Slf4jLoggerAdapter implements UniversalLogger {
    private final Logger logger;

    public Slf4jLoggerAdapter(Logger logger) {
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
