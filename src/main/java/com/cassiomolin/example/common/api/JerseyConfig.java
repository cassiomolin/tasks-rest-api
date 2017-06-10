package com.cassiomolin.example.common.api;

import com.cassiomolin.example.common.api.exception.mapper.ConstraintViolationExceptionMapper;
import com.cassiomolin.example.common.api.exception.mapper.JsonMappingExceptionMapper;
import com.cassiomolin.example.common.api.exception.mapper.JsonParseExceptionMapper;
import com.cassiomolin.example.common.api.filter.CorsFilter;
import com.cassiomolin.example.task.api.TaskResource;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

/**
 * Jersey configuration.
 *
 * @author cassiomolin
 */
@Component
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        registerResources();
        registerProviders();
    }

    private void registerResources() {
        register(TaskResource.class);
    }

    private void registerProviders() {
        register(CorsFilter.class);
        register(JacksonJaxbJsonProvider.class);
        register(ConstraintViolationExceptionMapper.class);
        register(JsonMappingExceptionMapper.class);
        register(JsonParseExceptionMapper.class);
    }
}