package com.notronix.albacore.json;

import com.google.gson.Gson;

abstract public class Bridge
{
    private static final Gson gson = new Gson();

    /**
     * Converts an object into its String notation.
     *
     * @param object the object being converted into String notation.
     * @return the String notation of the object.
     */
    public static String bridge(Object object)
    {
        return gson.toJson(object);
    }

    /**
     * Converts the String notation for an object into an object of the given <code>type</code>.
     *
     * @param string the String notation of an object.
     * @param type the type of object that the String notation will be converted into.
     * @param <T> the generic type of object that will be returned.
     * @return an object that is populated with all corresponding fields found in the String notation.
     */
    public static <T> T bridge(String string, Class<T> type)
    {
        return gson.fromJson(string, type);
    }
}
