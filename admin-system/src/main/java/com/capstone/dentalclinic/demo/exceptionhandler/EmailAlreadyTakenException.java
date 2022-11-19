package com.capstone.dentalclinic.demo.exceptionhandler;

public class EmailAlreadyTakenException extends Exception {
    
    public EmailAlreadyTakenException() {

    }

    public EmailAlreadyTakenException(String arg0) {
        super(arg0);
    }

    public EmailAlreadyTakenException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailAlreadyTakenException(Throwable cause) {
        super(cause);
    }

    protected EmailAlreadyTakenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
