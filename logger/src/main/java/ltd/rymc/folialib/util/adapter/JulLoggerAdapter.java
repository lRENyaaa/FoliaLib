package ltd.rymc.folialib.util.adapter;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JulLoggerAdapter implements UniversalLogger {
    private final Logger logger;

    public JulLoggerAdapter(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void info(String message) {
        logger.log(Level.INFO, message);
    }

    @Override
    public void warn(String message) {
        logger.log(Level.WARNING, message);
    }

    @Override
    public void error(String message) {
        logger.log(Level.SEVERE, message);
    }

    @Override
    public void debug(String message) {
        logger.log(Level.FINE, message);
    }

    @Override
    public void trace(String message) {
        logger.log(Level.FINER, message);
    }

    @Override
    public void error(String message, Throwable throwable) {
        logger.log(Level.SEVERE, message, throwable);
    }
}