package com.project.exception;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class IllegalActionExceptionTest {
    @Test
    public void test() {
        final IllegalActionException e = new IllegalActionException(
                "action is null");
        assertEquals(e.getStatus(), 400);
        assertEquals(e.getMessage(), "action is null");

        final IllegalActionException e2 = new IllegalActionException(
                "action is not allowed", new RuntimeException());
        assertEquals(e2.getStatus(), 400);
        assertEquals(e2.getMessage(), "action is not allowed");
    }
}
