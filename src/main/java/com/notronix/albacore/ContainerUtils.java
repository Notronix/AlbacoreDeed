package com.notronix.albacore;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

@SuppressWarnings("unused")
public abstract class ContainerUtils
{
    public static boolean thereAreMultiple(Collection<?> items)
    {
        return (numberOf(items) > 1);
    }

    public static boolean thereAreMultiple(Map<?, ?> items)
    {
        return (numberOf(items) > 1);
    }

    public static boolean thereAreOneOrMore(Collection<?> items)
    {
        return (numberOf(items) > 0);
    }

    public static boolean thereAreOneOrMore(Map<?, ?> items)
    {
        return (numberOf(items) > 0);
    }

    public static boolean thereAreNo(Collection<?> items)
    {
        return !thereAreOneOrMore(items);
    }

    public static boolean thereAreNo(Map<?, ?> items)
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
        return (items == null ? 0 : items.size());
    }

    public static int numberOf(Map<?, ?> items)
    {
        return (items == null ? 0 : items.size());
    }

    public static <T> List<List<T>> separateIntoGroups(List<T> items, int maxGroupSize) {
        if (thereAreNo(items)) {
            return emptyList();
        }

        if (numberOf(items) <= maxGroupSize) {
            return singletonList(items);
        }

        return range(0, items.size()).boxed().collect(groupingBy(index -> index / maxGroupSize)).values().stream()
                .map(indices -> indices.stream().map(items::get).collect(toList()))
                .collect(toList());
    }
}
