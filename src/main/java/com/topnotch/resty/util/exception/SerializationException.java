package com.topnotch.resty.util.exception;

/**
 * @author funmiayinde
 **/
public class SerializationException extends RuntimeException {

    public SerializationException(){

    }

    public SerializationException(String message) {
        super(message);
    }

    public SerializationException(String message, Throwable throwable){
        super(message, throwable);
    }
}
