package com.topnotch.resty.log;

/**
 * @author funmiayinde
 * */
public class JdkLoggerSupplier implements LoggerSupplier {


    @Override
    public Logger getLogger(Class clazz) {
        return new JdkLogger(java.util.logging.Logger.getLogger(clazz.getName()));
    }

    @Override
    public Logger getLogger(String className) {
        return new JdkLogger(java.util.logging.Logger.getLogger(className));
    }


}
