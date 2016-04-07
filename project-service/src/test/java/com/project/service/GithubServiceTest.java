package com.project.service;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import com.project.model.github.GithubEvent;

public class GithubServiceTest extends BaseSpringTest {
    @Autowired
    private GithubService service;

    @Test
    public void testNotNull() {
        assertNotNull(service);
    }

    @Test
    public void testGet() {
        List<GithubEvent> list = service.getPublicEvents();
        for (GithubEvent event : list) {
            assertNotNull(event);
            assertTrue(event.isPublic());
            assertNotNull(event.getId());
            assertNotNull(event.getType());
            assertNotNull(event.getActor());
        }
    }
}