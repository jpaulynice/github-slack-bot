package com.project.config;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class BaseJerseyTest extends JerseyTest {
    @Override
    @BeforeClass
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    @AfterClass
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Override
    protected Application configure() {
        forceSet(TestProperties.CONTAINER_PORT, "0");
        return new JerseyApplication().property("contextConfig",
                new AnnotationConfigApplicationContext(SpringConfig.class));
    }
}