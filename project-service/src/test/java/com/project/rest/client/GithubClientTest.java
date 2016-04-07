package com.project.rest.client;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.project.model.github.GithubEvent;
import com.project.service.BaseSpringTest;

public class GithubClientTest extends BaseSpringTest {
    @Autowired
    private GithubClient client;

    @Test
    public void testNotNull() {
        assertNotNull(client);
    }

    @Test
    public void testGet() {
        final List<GithubEvent> events = client.getEvents();
        assertNotNull(events);
        assertFalse(events.isEmpty());

        final List<GithubEvent> secondCall = client.getEvents();
        assertNotNull(secondCall);
        assertTrue(secondCall.isEmpty());
    }
}