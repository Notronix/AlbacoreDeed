package com.notronix.albacore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.math.BigInteger;

import static com.notronix.albacore.NumberUtils.getDoubleValue;
import static com.notronix.albacore.NumberUtils.getIntValue;
import static com.notronix.albacore.NumberUtils.getLongValue;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class NumberUtilsTest
{
    @Test(expected = NullPointerException.class)
    public void testGetLongValueForNullObject()
    {
        getLongValue(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLongValueForInvalidObject()
    {
        getLongValue(true);
    }

    @Test
    public void testGetLongValue()
    {
        assertEquals(10, getLongValue(10));
        assertEquals(11, getLongValue(new BigInteger("11")));
        assertEquals(12, getLongValue(12d));
        assertEquals(13, getLongValue(new BigDecimal(13)));
    }

    @Test(expected = NullPointerException.class)
    public void testGetDoubleValueForNullObject()
    {
        getDoubleValue(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetDoubleValueForInvalidObject()
    {
        getDoubleValue(true);
    }

    @Test
    public void testGetDoubleValue()
    {
        assertEquals(new Double(10), new Double(getDoubleValue(10)));
        assertEquals(new Double(11), new Double(getDoubleValue(new BigInteger("11"))));
        assertEquals(new Double(12), new Double(getDoubleValue(12d)));
        assertEquals(new Double(13), new Double(getDoubleValue(new BigDecimal(13))));
    }

    @Test(expected = NullPointerException.class)
    public void testGetIntValueForNullObject()
    {
        getIntValue(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetIntValueForInvalidObject()
    {
        getIntValue(true);
    }

    @Test
    public void testGetIntValue()
    {
        assertEquals(new Integer(10), new Integer(getIntValue(10)));
        assertEquals(new Integer(11), new Integer(getIntValue(new BigInteger("11"))));
        assertEquals(new Integer(12), new Integer(getIntValue(12d)));
        assertEquals(new Integer(13), new Integer(getIntValue(new BigDecimal(13.543))));
    }
}
