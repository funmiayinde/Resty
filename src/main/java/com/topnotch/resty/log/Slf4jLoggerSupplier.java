package com.topnotch.resty.log;


/**
* @author funmiayinde
* */
public class Slf4jLoggerSupplier implements LoggerSupplier {


    @Override
    public Logger getLogger(Class clazz) {
        return new Slf4jLogger(org.slf4j.LoggerFactory.getLogger(clazz));
    }

    @Override
    public Logger getLogger(String className) {
        return new Slf4jLogger(org.slf4j.LoggerFactory.getLogger(className));
    }
}
