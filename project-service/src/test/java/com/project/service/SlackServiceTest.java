package com.project.service;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.project.exception.SlackApiException;
import com.project.model.github.Author;
import com.project.model.github.GithubEvent;
import com.project.model.github.Organization;
import com.project.model.slack.SlackResponse;

public class SlackServiceTest extends BaseSpringTest {
    private Gson gson;

    @Autowired
    private SlackService service;

    @BeforeClass
    public void setup() {
        gson = new Gson();
    }

    @Test
    public void testNotNull() {
        assertNotNull(service);
    }

    @Test
    public void testPublish() {
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

        final SlackResponse res = service.publish(gson.toJson(event,
                GithubEvent.class));

        assertNotNull(res);
        assertTrue(res.isOk());
    }

    @Test(expectedExceptions = SlackApiException.class)
    public void testPushEventException() {
        service.publish("");
    }
}
