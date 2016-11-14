package com.openproject.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

@Provider
public class UnAuthorizedAccessExceptionMapper extends AbstractExceptionMapper<UnAuthorizedAccessException> {
    @Override
    protected Status getStatus() {
        return Response.Status.UNAUTHORIZED;
    }
}
