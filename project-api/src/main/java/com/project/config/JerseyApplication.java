package com.project.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import com.project.rest.provider.AppExceptionMapper;
import com.project.rest.v1.GithubResource;

public class JerseyApplication extends ResourceConfig {
    public JerseyApplication() {
        register(RequestContextFilter.class);
        register(AppExceptionMapper.class);
        register(GithubResource.class);
    }
}