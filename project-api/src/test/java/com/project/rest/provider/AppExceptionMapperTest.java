/**
 *
 */
package com.project.rest.provider;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.project.config.BaseSpringTest;

public class AppExceptionMapperTest extends BaseSpringTest {
    @Autowired
    private AppExceptionMapper mapper;

    /**
     * Test mapper created properly
     */
    @Test
    public void testNotNull() {
        assertNotNull(mapper);
    }

    /**
     * Test map exceptions
     */
    @Test
    public void testExceptions() {
        Response r = mapper.toResponse(new RuntimeException());

        assertNotNull(r);
        assertEquals(r.getStatus(), 500);

        r = mapper.toResponse(new WebApplicationException());

        assertNotNull(r);
        assertEquals(r.getStatus(), 500);
    }
}