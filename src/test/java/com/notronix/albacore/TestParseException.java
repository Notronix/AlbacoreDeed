package com.notronix.albacore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(JUnit4.class)
public class TestParseException
{
    @Test
    public void test() {
        @SuppressWarnings("ThrowableNotThrown")
        Throwable cause = new RuntimeException();
        String message = "this is the message";

        ParseException pe = new ParseException(message);
        assertNull(pe.getCause());
        assertEquals(message, pe.getMessage());

        pe = new ParseException(message, cause);
        assertEquals(message, pe.getMessage());
        assertEquals(cause, pe.getCause());
    }
}
