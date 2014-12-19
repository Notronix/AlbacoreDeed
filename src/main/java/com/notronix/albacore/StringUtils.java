package com.notronix.albacore;

public abstract class StringUtils
{
    public static boolean isBlank(String string)
    {
        return org.apache.commons.lang3.StringUtils.isBlank(string);
    }

    public static boolean isNotBlank(String string)
    {
        return !isBlank(string);
    }
}
