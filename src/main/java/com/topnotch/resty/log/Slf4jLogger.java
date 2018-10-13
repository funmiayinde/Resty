package com.topnotch.resty.log;

import com.topnotch.resty.util.Painter;

/**
 * @author funmiayinde
 */
public class Slf4jLogger extends Logger {

    private org.slf4j.Logger logger;

    public Slf4jLogger(org.slf4j.Logger logger) {
        this.logger = logger;
    }

    public void d(String message) {
        logger.debug(Painter.cyan(message));
    }

    public void d(String message, Object... args) {
        logger.debug(Painter.cyan(String.format(message, args)));
    }

    public void d(String message, Throwable throwable, Object... args) {
        logger.debug(Painter.cyan(String.format(message, args)), throwable);
    }

    public void d(String message, Throwable throwable) {
        logger.debug(Painter.cyan(message), throwable);
    }

    public void i(String message) {
        logger.info(Painter.blue(message));
    }

    public void i(String message, Object... args) {
        logger.info(Painter.blue(String.format(message, args)));
    }

    public void i(String message, Throwable throwable, Object... args) {
        logger.info(Painter.blue(String.format(message, args)), throwable);
    }

    public void i(String message, Throwable throwable) {
        logger.info(Painter.blue(message), throwable);
    }

    public void w(String message) {
        logger.warn(Painter.yellow(message));
    }

    public void w(String message, Object... args) {
        logger.warn(Painter.yellow(String.format(message, args)));
    }

    public void w(String message, Throwable throwable, Object... args) {
        logger.warn(Painter.yellow(String.format(message, args)), throwable);
    }

    public void w(String message, Throwable throwable) {
        logger.warn(Painter.yellow(message), throwable);
    }

    public void e(String message) {
        logger.error(Painter.yellow(message));
    }

    public void e(String message, Object... args) {
        logger.error(Painter.yellow(String.format(message, args)));
    }

    public void e(String message, Throwable throwable, Object... args) {
        logger.error(Painter.yellow(String.format(message, args)), throwable);
    }

    public void e(String message, Throwable throwable) {
        logger.error(Painter.yellow(message), throwable);
    }

    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    public boolean isWarnedEnabled() {
        return logger.isWarnEnabled();
    }

    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

}
