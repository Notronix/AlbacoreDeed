package com.notronix.albacore;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.apache.commons.collections4.MapUtils.isEmpty;

public abstract class ContainerUtils
{
    public static boolean thereAreMultiple(Collection<?> items)
    {
        return (items != null && items.size() > 1);
    }

    public static boolean thereAreOneOrMore(Collection<?> items)
    {
        return (items != null && items.size() > 0);
    }

    public static boolean thereAreOneOrMore(Map<?, ?> items)
    {
        return (items != null && !items.isEmpty());
    }

    public static boolean thereAreNo(Collection<?> items)
    {
        return !thereAreOneOrMore(items);
    }

    public static <T> T theFirst(Collection<T> items)
    {
        if (thereAreNo(items))
        {
            return null;
        }

        return items.iterator().next();
    }

    public static <T> T theSecond(Collection<T> items)
    {
        if (numberOf(items) < 2)
        {
            return null;
        }

        if (items instanceof List)
        {
            return ((List<T>) items).get(1);
        }

        Iterator<T> iterator = items.iterator();
        iterator.next();

        return iterator.next();
    }

    public static int numberOf(Collection<?> items)
    {
        if (thereAreNo(items))
        {
            return 0;
        }

        return items.size();
    }

    public static String printMap(Map<?, ?> map)
    {
        if (isEmpty(map))
        {
            return "";
        }

        String retVal = "";

        for (Map.Entry entry : map.entrySet())
        {
            if (StringUtils.isNotBlank(retVal))
            {
                retVal += "; ";
            }

            retVal += entry.getKey() + ": " + entry.getValue();
        }

        return retVal;
    }
}
