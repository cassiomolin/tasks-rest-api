package com.cassiomolin.example.common.api.config;

import com.cassiomolin.example.common.api.exceptionmapper.ConstraintViolationExceptionMapper;
import com.cassiomolin.example.common.api.exceptionmapper.JsonMappingExceptionMapper;
import com.cassiomolin.example.common.api.exceptionmapper.JsonParseExceptionMapper;
import com.cassiomolin.example.common.api.filter.CorsFilter;
import com.cassiomolin.example.common.api.provider.ObjectMapperProvider;
import com.cassiomolin.example.task.api.resource.TaskResource;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

/**
 * Jersey configuration class.
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
        register(ObjectMapperProvider.class);
    }
}