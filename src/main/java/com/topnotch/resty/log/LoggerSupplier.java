package com.topnotch.resty.log;

/**
 * @author funmiayinde
 */
public interface LoggerSupplier {

    public Logger getLogger(Class clazz);

    public Logger getLogger(String className);
}
