package com.cassiomolin.example.common.api.exception;

import javax.ws.rs.ClientErrorException;

/**
 * A runtime exception indicating a request entity sent by a client cannot be processed.
 *
 * @author cassiomolin
 */
public class UnprocessableEntityException extends ClientErrorException {

    private static final Integer STATUS = 422;

    public UnprocessableEntityException() {
        super(STATUS);
    }

    public UnprocessableEntityException(String message) {
        super(message, STATUS);
    }

    public UnprocessableEntityException(Throwable cause) {
        super(STATUS, cause);
    }

    public UnprocessableEntityException(String message, Throwable cause) {
        super(message, STATUS, cause);
    }
}