package com.project.rest.v1;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.testng.annotations.Test;

import com.project.config.BaseJerseyTest;
import com.project.model.Message;
import com.project.model.StartRequest;
import com.project.model.Status;

public class GithubResourceTest extends BaseJerseyTest {
    @Test
    public void testStartStop() throws InterruptedException {
        final StartRequest req = new StartRequest(0, 2);
        final Response response = target("scheduler/actions/start").request()
                .post(Entity.json(req), Response.class);

        assertNotNull(response);
        assertEquals(response.getStatus(), 202);

        // let the scheduler run
        Thread.sleep(2000);

        // already started
        final Response r2 = target("scheduler/actions/start").request().post(
                Entity.json(req), Response.class);

        assertNotNull(r2);
        assertEquals(r2.getStatus(), 400);

        // test stop
        final Response r = target("scheduler/actions/stop").request().post(
                Entity.json(null), Response.class);

        assertNotNull(r);
        assertEquals(r.getStatus(), 202);

        // already stopped
        final Response r3 = target("scheduler/actions/stop").request().post(
                Entity.json(null), Response.class);

        assertNotNull(r3);
        assertEquals(r3.getStatus(), 400);
    }

    @Test
    public void testBadFrequency() {
        final StartRequest req = new StartRequest(1, 0);
        final Response r = target("scheduler/actions/start").request().post(
                Entity.json(req), Response.class);

        assertNotNull(r);
        assertEquals(r.getStatus(), 400);

        final Message m = r.readEntity(Message.class);
        assertNotNull(m);
        assertEquals(m.getStatus(), 400);
        assertEquals(m.getInfo(), "frequency must be greater than 0");
        assertEquals(m.toString(),
                "Message [status=400, info=frequency must be greater than 0]");
    }

    @Test
    public void testBadDelay() {
        final StartRequest req = new StartRequest(-11, 1);
        final Response r = target("scheduler/actions/start").request().post(
                Entity.json(req), Response.class);

        assertNotNull(r);
        assertEquals(r.getStatus(), 400);

        final Message m = r.readEntity(Message.class);
        assertNotNull(m);
        assertEquals(m.getStatus(), 400);
        assertEquals(m.getInfo(), "delay must be greater than or equal to 0");
        assertEquals(m.toString(),
                "Message [status=400, info=delay must be greater than or equal to 0]");
    }

    @Test
    public void testGetStatus() {
        final Response r = target("scheduler/status").request().get();
        assertNotNull(r);
        assertEquals(r.getStatus(), 200);

        final Status s = r.readEntity(Status.class);
        assertNotNull(s);
        assertEquals(s.isRunning(), false);
    }
}