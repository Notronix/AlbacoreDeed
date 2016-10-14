package com.notronix.albacore;

public abstract class NumberUtils
{
    public static int intValueOf(Object object) throws NullPointerException, IllegalArgumentException
    {
        if (object == null)
        {
            throw new NullPointerException("object is null.");
        }

        if (object instanceof Number)
        {
            return ((Number) object).intValue();
        }

        throw new IllegalArgumentException(object.getClass().getSimpleName() + " is not a Number instance.");
    }

    public static long longValueOf(Object object)
    {
        if (object == null)
        {
            throw new NullPointerException("object is null.");
        }

        if (object instanceof Number)
        {
            return ((Number) object).longValue();
        }

        throw new IllegalArgumentException(object.getClass().getSimpleName() + " is not a Number instance.");
    }

    public static double doubleValueOf(Object object) throws IllegalArgumentException
    {
        if (object == null)
        {
            throw new NullPointerException("object is null.");
        }

        if (object instanceof Number)
        {
            return ((Number) object).doubleValue();
        }

        throw new IllegalArgumentException(object.getClass().getSimpleName() + " is not a Number instance.");
    }
}
