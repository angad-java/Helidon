package io.helidon.example.jpa;

import io.helidon.exception.HelidonbarExceptionMapper;
import io.helidon.fault.HelidonbarFaultToleranceApi;
import io.helidon.fault.IHelidonbarFaultToleranceApi;

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Simple JAXRS Application that registers one resource class.
 */
@ApplicationScoped
@ApplicationPath("/")
public class ExampleApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes =new HashSet<>();
        classes.add(ExampleResource.class);
        classes.add(HelidonbarExceptionMapper.class);
        classes.add(HelidonbarFaultToleranceApi.class);
        classes.add(IHelidonbarFaultToleranceApi.class);
        return classes;
    }
}
