package com.cassiomolin.example.common.api.exceptionmapper;

import com.cassiomolin.example.common.api.model.ApiError;
import com.fasterxml.jackson.databind.JsonMappingException;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.time.Instant;

/**
 * Component that maps a {@link JsonMappingException} to a HTTP response.
 *
 * @author cassiomolin
 */
@Provider
public class JsonMappingExceptionMapper implements ExceptionMapper<JsonMappingException> {

    @Context
    private UriInfo uriInfo;

    @Override
    public Response toResponse(JsonMappingException exception) {

        ApiError error = new ApiError();
        error.setTimestamp(Instant.now().toEpochMilli());
        error.setStatus(Status.BAD_REQUEST.getStatusCode());
        error.setError(Status.BAD_REQUEST.getReasonPhrase());
        error.setMessage("Request JSON cannot be parsed");
        error.setPath(uriInfo.getAbsolutePath().getPath());

        return Response.status(Status.BAD_REQUEST).entity(error).type(MediaType.APPLICATION_JSON).build();
    }
}