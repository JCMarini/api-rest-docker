package com.jcmc.demo.core.util;

import org.slf4j.LoggerFactory;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class Logger {

    private static final Map<Class, Logger> LOGGERS;
    private final org.slf4j.Logger logger;
    private final Random random;
    private static final String EID_PATTERN = "%d";

    private enum Level {
        INFO, WARN, DEBUG, ERROR, FATAL
    }

    static {
        LOGGERS = new HashMap<>();
    }

    private Logger(org.slf4j.Logger logger) {
        this.logger = logger;
        this.random = new Random();
    }

    public static <T> Logger getLogger(Class<T> clazz) {
        Logger log = LOGGERS.get(clazz);
        if (log == null) {
            log = new Logger(LoggerFactory.getLogger(clazz));
            LOGGERS.put(clazz, log);
        }
        return log;
    }

    public static String generateUId() {
        String uuid = UUID.randomUUID().toString();
        BigInteger numid = new BigInteger(1, uuid.getBytes());
        return Integer.toString(numid.intValue());
    }

    private void log(String message, Throwable th, Level level) {
        switch (level) {
            case INFO:
                logger.info(message, th);
                break;
            case WARN:
                logger.warn(message, th);
                break;
            case DEBUG:
                logger.debug(message, th);
                break;
            case ERROR:
                logger.error(message, th);
                break;
        }
    }


    public void info(String message) {
        log(message, null, Level.INFO);
    }

    public void info(String message, Throwable th) {
        log(message, th, Level.INFO);
    }

    public void warn(String message, Throwable th) {
        log(message, th, Level.WARN);
    }

    public void debug(String message, Throwable th) {
        log(message, th, Level.DEBUG);
    }

    public void error(String message, Throwable th) {
        log(message, th, Level.ERROR);
    }
}
