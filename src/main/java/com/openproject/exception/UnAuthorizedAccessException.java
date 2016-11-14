package com.openproject.exception;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

public class UnAuthorizedAccessException extends ClientErrorException {

    private static final long serialVersionUID = -8678545963805618170L;

    /**
     * Construct a new "access forbidden" exception.
     */
    public UnAuthorizedAccessException() {
        super(Response.Status.UNAUTHORIZED);
    }

    /**
     * Construct a new "access forbidden" exception.
     * 
     * @param cause
     *            the underlying cause of the exception.
     */
    public UnAuthorizedAccessException(final Throwable cause) {
        super(Response.Status.UNAUTHORIZED, cause);
    }

}
