package com.notronix.albacore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.notronix.albacore.StringUtils.isBlank;
import static com.notronix.albacore.StringUtils.isNotBlank;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class StringUtilsTest
{
    @Test
    public void testIsBlank()
    {
        assertTrue("Value should be blank.", isBlank(null));
        assertTrue("Value should be blank.", isBlank(""));
        assertTrue("Value should be blank.", isBlank("      "));
        assertFalse("Value should not be blank.", isBlank("   d   "));
    }

    @Test
    public void testIsNotBlank()
    {
        assertFalse("Value should be blank.", isNotBlank(null));
        assertFalse("Value should be blank.", isNotBlank(""));
        assertFalse("Value should be blank.", isNotBlank("      "));
        assertTrue("Value should not be blank.", isNotBlank("   d   "));
    }
}
