package com.project.model.github;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Date;

import org.testng.annotations.Test;

import com.google.gson.Gson;

public class GithubEventTest {
    private final Gson gson = new Gson();

    @Test
    public void test() {
        final GithubEvent event = new GithubEvent();
        event.setId(123456L);
        event.setType("Commit");
        event.setCreated(new Date());

        final Organization org = new Organization();
        org.setId(299L);
        org.setLogin("julesbond007");
        org.setUrl("www.myorg.org");

        event.setOrg(org);
        event.setPublic(true);

        final Author actor = new Author();
        actor.setLogin("julesbond007");
        actor.setEmail("jay.paulynice@gmail.com");
        event.setActor(actor);

        final Repository rep = new Repository();
        rep.setId(1222L);
        rep.setName("myrepo");
        rep.setUrl("github.com/julesbond007/myrepo");

        event.setRepo(rep);

        final Payload p = new Payload();
        p.setPushId(1243L);
        p.setBefore("fjfjfj");
        p.setHead("ajdjdjj");
        p.setRef("fjfjfjslalal");

        final Commit c = new Commit();
        c.setAuthor(actor);
        c.setMessage("commit message");
        c.setSha("fjajaj");
        c.setUrl("someurl");

        p.setCommits(Arrays.asList(c));

        event.setPayload(p);

        assertNotNull(event.toString());

        final String json = gson.toJson(event, GithubEvent.class);
        assertNotNull(json);

        // read back from json to event
        final GithubEvent back = gson.fromJson(json, GithubEvent.class);
        assertEquals(back.getId(), event.getId());
        assertEquals(back.getType(), event.getType());

        // org
        assertNotNull(back.getOrg());
        assertEquals(back.getOrg().getId(), event.getOrg().getId());
        assertEquals(back.getOrg().getLogin(), event.getOrg().getLogin());
        assertEquals(back.getOrg().getUrl(), event.getOrg().getUrl());

        // actor
        assertNotNull(back.getActor());
        assertEquals(back.getActor().getLogin(), event.getActor().getLogin());
        assertEquals(back.getActor().getEmail(), event.getActor().getEmail());

        // repo
        assertNotNull(back.getRepo());
        assertEquals(back.getRepo().getId(), event.getRepo().getId());
        assertEquals(back.getRepo().getName(), event.getRepo().getName());
        assertEquals(back.getRepo().getUrl(), event.getRepo().getUrl());

        // payload
        assertNotNull(back.getPayload());
        assertEquals(back.getPayload().getPushId(), event.getPayload()
                .getPushId());
    }
}
