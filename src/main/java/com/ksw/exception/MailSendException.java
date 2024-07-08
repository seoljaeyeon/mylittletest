package com.ksw.exception;

public class MailSendException extends RuntimeException {
    public MailSendException(String message) {
        super(message);
    }
}
