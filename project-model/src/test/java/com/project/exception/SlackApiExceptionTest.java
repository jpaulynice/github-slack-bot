package com.project.exception;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class SlackApiExceptionTest {
    @Test
    public void test() {
        final SlackApiException e = new SlackApiException("invalid token");
        assertEquals(e.getStatus(), 404);
        assertEquals(e.getMessage(), "invalid token");

        final SlackApiException e2 = new SlackApiException("invalid channel",
                new RuntimeException());
        assertEquals(e2.getStatus(), 404);
        assertEquals(e2.getMessage(), "invalid channel");
    }
}