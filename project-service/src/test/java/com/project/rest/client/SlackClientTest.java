package com.project.rest.client;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.project.model.github.Author;
import com.project.model.github.GithubEvent;
import com.project.model.github.Organization;
import com.project.model.slack.SlackResponse;
import com.project.service.BaseSpringTest;

public class SlackClientTest extends BaseSpringTest {
    @Autowired
    private SlackClient client;

    @Value("${slack.message.api}")
    private String url;

    @Test
    public void testNotNull() {
        assertNotNull(client);
    }

    @Test
    public void testPushEvent() {
        final GithubEvent event = new GithubEvent();
        event.setId(123456L);
        event.setCreated(new Date());

        final Organization org = new Organization();
        org.setId(299L);

        event.setOrg(org);
        event.setPublic(true);

        final Author actor = new Author();
        actor.setLogin("julesbond007");
        event.setActor(actor);

        final Gson gson = new Gson();
        final SlackResponse res = client.publish(url
                + gson.toJson(event, GithubEvent.class));
        assertNotNull(res);
        assertTrue(res.isOk());
    }
}