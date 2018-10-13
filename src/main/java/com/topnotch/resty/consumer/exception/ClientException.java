package com.topnotch.resty.consumer.exception;

public class ClientException extends RuntimeException {

    /*
     * constructor/
     */
    public ClientException() {

    }

    /**
     * Constructor
     *
     * @param message Exception message
     */
    public ClientException(String message) {
        super(message);
    }

    /**
     * Constructor
     *
     * @param message Exception Message
     * @param cause   An Exception
     */
    public ClientException(String message, Exception cause) {
        super(message, cause);
    }


}
