package com.project.service;

import static org.testng.Assert.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class EmailServiceTest extends BaseSpringTest {
    @Autowired
    private EmailService service;

    @Test
    public void testNotNull() {
        assertNotNull(service);
    }

    @Test
    public void testSend() {
        service.send(" failed");
    }
}