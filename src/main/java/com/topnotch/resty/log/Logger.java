package com.topnotch.resty.log;


/**
 * @author funmiayinde 09/10/2018
 */
public abstract class Logger {

    private static LoggerSupplier loggerSupplier;

    static {
        try {
            Class.forName("org.slf4j.Logger");
            loggerSupplier = new Slf4jLoggerSupplier();
        } catch (ClassNotFoundException e) {
            loggerSupplier = new JdkLoggerSupplier();
        }
    }

    public static Logger getLogger(Class clazz) {
        return loggerSupplier.getLogger(clazz);
    }

    public static Logger getLogger(String clazzName) {
        return loggerSupplier.getLogger(clazzName);
    }

    /*
     * Debug
     * */
    public abstract void d(String message);

    public abstract void d(String message, Object... args);

    public abstract void d(String message, Throwable throwable, Object... args);

    public abstract void d(String message, Throwable throwable);

    /*
     * INFO
     * */
    public abstract void i(String message);

    public abstract void i(String message, Object... args);

    public abstract void i(String message, Throwable throwable, Object... args);

    public abstract void i(String message, Throwable throwable);

    /*
     * WARNING
     * */
    public abstract void w(String message);

    public abstract void w(String message, Object... args);

    public abstract void w(String message, Throwable throwable, Object... args);

    public abstract void w(String message, Throwable throwable);

    /*
     * ERROR
     * */
    public abstract void e(String message);

    public abstract void e(String message, Object... args);

    public abstract void e(String message, Throwable throwable, Object... args);

    public abstract void e(String message, Throwable throwable);

    public abstract boolean isDebugEnabled();

    public abstract boolean isInfoEnabled();

    public abstract boolean isWarningEnabled();

    public abstract boolean isErrorEnabled();
}
