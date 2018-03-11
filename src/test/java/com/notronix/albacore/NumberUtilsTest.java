package com.notronix.albacore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.math.BigInteger;

import static com.notronix.albacore.NumberUtils.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(JUnit4.class)
public class NumberUtilsTest
{
    @Test(expected = NullPointerException.class)
    public void testGetLongValueForNullObject() {
        longValueOf(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLongValueForInvalidObject() {
        longValueOf(true);
    }

    @Test
    public void testGetLongValue() {
        assertEquals(10, longValueOf(10));
        assertEquals(11, longValueOf(new BigInteger("11")));
        assertEquals(12, longValueOf(12d));
        assertEquals(13, longValueOf(new BigDecimal(13)));
    }

    @Test(expected = NullPointerException.class)
    public void testGetDoubleValueForNullObject() {
        doubleValueOf(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetDoubleValueForInvalidObject() {
        doubleValueOf(true);
    }

    @Test
    public void testGetDoubleValue() {
        assertEquals(new Double(10), new Double(doubleValueOf(10)));
        assertEquals(new Double(11), new Double(doubleValueOf(new BigInteger("11"))));
        assertEquals(new Double(12), new Double(doubleValueOf(12d)));
        assertEquals(new Double(13), new Double(doubleValueOf(new BigDecimal(13))));
    }

    @Test(expected = NullPointerException.class)
    public void testGetIntValueForNullObject() {
        intValueOf(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetIntValueForInvalidObject() {
        intValueOf(true);
    }

    @Test
    public void testGetIntValue() {
        assertEquals(new Integer(10), new Integer(intValueOf(10)));
        assertEquals(new Integer(11), new Integer(intValueOf(new BigInteger("11"))));
        assertEquals(new Integer(12), new Integer(intValueOf(12d)));
        assertEquals(new Integer(13), new Integer(intValueOf(new BigDecimal(13.543))));
    }

    @Test
    public void testParseDouble() {
        try {
            assertEquals(new Double(2), parseDoubleValue("2"));
            assertEquals(new Double(2), parseDoubleValue("2.0"));
            assertEquals(new Double(2), parseDoubleValue("$2.0"));
            assertEquals(new Double(2), parseDoubleValue("Price: $2.00"));
            assertEquals(new Double(2), parseDoubleValue("Price: $2.0 USD"));
            assertEquals(new Double(2000000.99), parseDoubleValue("Price: $2,000,000.99"));
        }
        catch (Exception ex) {
            fail("No exceptions should have been thrown.");
        }

        try {
            parseDoubleValue(null);
            fail("ParseException should have been thrown.");
        }
        catch (ParseException ex) {
            // expected
        }

        try {
            parseDoubleValue("");
            fail("ParseException should have been thrown.");
        }
        catch (ParseException ex) {
            // expected
        }

        try {
            parseDoubleValue("two");
            fail("ParseException should have been thrown.");
        }
        catch (ParseException ex) {
            // expected
        }
    }
}
