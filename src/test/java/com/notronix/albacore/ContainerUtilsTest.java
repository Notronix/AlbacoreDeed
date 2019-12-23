package com.notronix.albacore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.*;
import java.util.stream.Collectors;

import static com.notronix.albacore.ContainerUtils.*;
import static java.util.stream.IntStream.range;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class ContainerUtilsTest
{
    @Test
    public void testNone()
    {
        assertTrue("There should be no items found.", thereAreNo((Collection<?>) null));
        assertTrue("There should be no items found.", thereAreNo((Map<?, ?>) null));

        assertTrue("There should be no items found.", thereAreNo(Collections.emptyList()));
        assertTrue("There should be no items found.", thereAreNo(Collections.emptyMap()));

        assertFalse("There should be items found.", thereAreNo(Collections.singletonList("1")));
        assertFalse("There should be items found.", thereAreNo(Arrays.asList("1", "2")));

        Map<String, String> multiItemMap = new HashMap<>();
        multiItemMap.put("key1", "value1");
        multiItemMap.put("key2", "value2");
        assertFalse("There should be items found.", thereAreNo(multiItemMap));
        assertFalse("There should be items found.", thereAreNo(Collections.singletonMap("key", "value")));
    }

    @Test
    public void testOneOrMore()
    {
        assertFalse("There should not be one or more items found.", thereAreOneOrMore((Collection<?>) null));
        assertFalse("There should not be one or more items found.", thereAreOneOrMore(Collections.emptyList()));
        assertTrue("There should be one or more items found.", thereAreOneOrMore(Collections.singletonList("1")));
        assertTrue("There should be one or more items found.", thereAreOneOrMore(Arrays.asList("1", "2")));

        assertFalse("There should not be one or more items found.", thereAreOneOrMore((Map<?, ?>) null));
        Map<Object, Object> map = new HashMap<>();
        assertFalse("There should not be one or more items found.", thereAreOneOrMore(map));
        map.put("1", "1");
        assertTrue("There should be one or more items found.", thereAreOneOrMore(map));
        map.put("2", "2");
        assertTrue("There should be one or more items found.", thereAreOneOrMore(map));
    }

    @Test
    public void testMultiple()
    {
        assertFalse("There should not be multiple items found.", thereAreMultiple((Collection<?>) null));
        assertFalse("There should not be multiple items found.", thereAreMultiple((Map<?, ?>) null));

        assertFalse("There should not be multiple items found.", thereAreMultiple(Collections.emptyList()));
        assertFalse("There should not be multiple items found.", thereAreMultiple(Collections.singletonList("1")));
        assertTrue("There should be multiple items found.", thereAreMultiple(Arrays.asList("1", "2")));

        Map<String, String> multiItemMap = new HashMap<>();
        multiItemMap.put("key1", "value1");
        multiItemMap.put("key2", "value2");
        assertTrue("There should be multiple items found.", thereAreMultiple(multiItemMap));
        assertFalse("There should not be multiple items found.", thereAreMultiple(Collections.emptyMap()));
        assertFalse("There should not be multiple items found.", thereAreMultiple(Collections.singletonMap("key", "value")));
    }

    @Test
    public void testFirst()
    {
        String s1 = "s1";
        String s2 = "s2";

        assertNull("Nothing should be returned.", theFirst(null));
        assertNull("Nothing should be returned.", theFirst(Collections.emptyList()));
        assertEquals("First item is wrong.", s1, theFirst(Arrays.asList(s1, s2)));
        assertEquals("First item is wrong.", s2, theFirst(Arrays.asList(s2, s1)));
    }

    @Test
    public void testSecond()
    {
        String s1 = "s1";
        String s2 = "s2";
        String s3 = "s3";

        assertNull("Nothing should be returned.", theSecond(null));
        assertNull("Nothing should be returned.", theSecond(Collections.emptyList()));
        assertEquals("First item is wrong.", s2, theSecond(Arrays.asList(s1, s2, s3)));
        assertEquals("First item is wrong.", s1, theSecond(Arrays.asList(s2, s1, s3)));

        Collection<String> strings = new HashSet<>(3);
        strings.add(s1);
        strings.add(s2);
        strings.add(s3);

        String first = theFirst(strings);
        String second = theSecond(strings);

        assertNotEquals("Strings should be different.", first, second);
        assertTrue("Wrong string returned.", strings.contains(theSecond(strings)));
    }

    @Test
    public void testSize()
    {
        assertEquals("Size should be 0.", 0, numberOf((Collection<?>) null));
        assertEquals("Size should be 0.", 0, numberOf((Map<?, ?>) null));

        assertEquals("Size should be 0.", 0, numberOf(Collections.emptyList()));
        assertEquals("Size should be 1.", 1, numberOf(Collections.singletonList("1")));
        assertEquals("Size should be 3.", 3, numberOf(Arrays.asList("1", "2", "3")));

        Map<String, String> multiItemMap = new HashMap<>();
        multiItemMap.put("key1", "value1");
        multiItemMap.put("key2", "value2");
        multiItemMap.put("key3", "value3");
        assertEquals("Size should be 3.", 3, numberOf(multiItemMap));
        assertEquals("Size should be 0.", 0, numberOf(Collections.emptyMap()));
        assertEquals("Size should be 1.", 1, numberOf(Collections.singletonMap("key", "value")));
    }

    @Test
    public void testSeparateIntoGroups() {
        assertNotNull("result should not be null", separateIntoGroups(null, 2));
        assertEquals("size should be 0", 0, separateIntoGroups(null, 2).size());

        List<String> strings = range(0, 10).boxed().map(Object::toString).collect(Collectors.toList());
        assertEquals("size should be 1", 1, separateIntoGroups(strings, 20).size());
        assertEquals("size should be 2", 2, separateIntoGroups(strings, 5).size());
        assertEquals("size should be 4", 4, separateIntoGroups(strings, 3).size());
    }
}
