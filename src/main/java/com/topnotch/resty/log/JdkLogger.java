package com.topnotch.resty.log;

import com.topnotch.resty.util.Painter;

import java.util.logging.Level;

/**
 * @author funmiayinde
 */
public class JdkLogger extends Logger {

    private java.util.logging.Logger logger;
    private String clazzName;

    public JdkLogger(java.util.logging.Logger logger) {
        this.logger = logger;
    }

    public void d(String message) {
        logger.logp(Level.FINE, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), Painter.cyan(message));
    }

    public void d(String message, Throwable throwable) {
        logger.logp(Level.FINE, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), Painter.cyan(message), throwable);
    }

    public void d(String message, Throwable throwable, Object... args) {
        logger.logp(Level.FINE, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), Painter.cyan(String.format(message, args)), throwable);
    }

    public void d(String message, Object... args) {
        logger.logp(Level.FINE, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), Painter.cyan(String.format(message, args)));
    }

    // info
    public void i(String message) {
        logger.logp(Level.INFO, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), Painter.blue(message));
    }

    public void i(String message, Throwable throwable) {
        logger.logp(Level.INFO, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), Painter.blue(message), throwable);
    }

    public void i(String message, Throwable throwable, Object... args) {
        logger.logp(Level.INFO, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), Painter.blue(String.format(message, args)), throwable);
    }

    public void i(String message, Object... args) {
        logger.logp(Level.INFO, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), Painter.blue(String.format(message, args)));
    }

    // warning
    public void w(String message) {
        logger.logp(Level.WARNING, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), Painter.yellow(message));
    }

    public void w(String message, Throwable throwable) {
        logger.logp(Level.WARNING, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), Painter.yellow(message), throwable);
    }

    public void w(String message, Throwable throwable, Object... args) {
        logger.logp(Level.WARNING, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), Painter.yellow(String.format(message, args)), throwable);
    }

    public void w(String message, Object... args) {
        logger.logp(Level.WARNING, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), Painter.yellow(String.format(message, args)));
    }

    // Error
    public void e(String message) {
        logger.logp(Level.SEVERE, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), Painter.red(message));
    }

    public void e(String message, Throwable throwable) {
        logger.logp(Level.SEVERE, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), Painter.red(message), throwable);
    }

    public void e(String message, Throwable throwable, Object... args) {
        logger.logp(Level.SEVERE, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), Painter.red(String.format(message, args)), throwable);
    }

    public void e(String message, Object... args) {
        logger.logp(Level.SEVERE, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), Painter.red(String.format(message, args)));
    }

    public boolean isDebugEnabled() {
        return logger.isLoggable(Level.FINE);
    }

    public boolean isInfoEnabled() {
        return logger.isLoggable(Level.INFO);
    }

    public boolean isErrorEnabled() {
        return logger.isLoggable(Level.SEVERE);
    }


}
