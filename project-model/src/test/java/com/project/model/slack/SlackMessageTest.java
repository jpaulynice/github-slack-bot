package com.project.model.slack;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.google.gson.Gson;

public class SlackMessageTest {
    private final Gson gson = new Gson();

    @Test
    public void testToJson() {
        final SlackResponse res = new SlackResponse();
        res.setChannel("test-channel");
        res.setError("error");
        res.setOk(true);

        final SlackMessage message = new SlackMessage();
        message.setSubtype("subtype");
        message.setText("texting");
        message.setUsername("julesbond007");
        message.setType("type");

        res.setMessage(message);
        assertNotNull(res.toString());

        final String r = gson.toJson(res);
        assertNotNull(r);

        final SlackResponse m2 = gson.fromJson(r, SlackResponse.class);
        assertNotNull(m2);
        assertTrue(m2.isOk());
        assertEquals(m2.getChannel(), "test-channel");
        assertEquals(m2.getError(), "error");

        final SlackMessage s = m2.getMessage();
        assertNotNull(s);
        assertEquals(s.getUsername(), "julesbond007");
        assertEquals(s.getText(), "texting");
        assertEquals(s.getType(), "type");
        assertEquals(s.getSubtype(), "subtype");
    }
}