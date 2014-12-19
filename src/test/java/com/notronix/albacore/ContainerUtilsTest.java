package com.notronix.albacore;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.*;

import static com.notronix.albacore.ContainerUtils.*;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class ContainerUtilsTest
{
    @Test
    public void testNone()
    {
        assertTrue("There should be no items found.", thereAreNo(null));
        assertTrue("There should be no items found.", thereAreNo(Arrays.asList()));
        assertFalse("There should be items found.", thereAreNo(Arrays.asList("1")));
        assertFalse("There should be items found.", thereAreNo(Arrays.asList("1", "2")));
    }

    @Test
    public void testOneOrMore()
    {
        assertFalse("There should not be one or more items found.", thereAreOneOrMore((Collection<?>) null));
        assertFalse("There should not be one or more items found.", thereAreOneOrMore(Arrays.asList()));
        assertTrue("There should be one or more items found.", thereAreOneOrMore(Arrays.asList("1")));
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
        assertFalse("There should not be multiple items found.", thereAreMultiple(null));
        assertFalse("There should not be multiple items found.", thereAreMultiple(Arrays.asList()));
        assertFalse("There should not be multiple items found.", thereAreMultiple(Arrays.asList("1")));
        assertTrue("There should be multiple items found.", thereAreMultiple(Arrays.asList("1", "2")));
    }

    @Test
    public void testFirst()
    {
        String s1 = "s1";
        String s2 = "s2";

        assertNull("Nothing should be returned.", theFirst(null));
        assertNull("Nothing should be returned.", theFirst(Arrays.asList()));
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
        assertNull("Nothing should be returned.", theSecond(Arrays.asList()));
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
        assertEquals("Size should be 0.", 0, numberOf(null));
        assertEquals("Size should be 0.", 0, numberOf(Arrays.asList()));
        assertEquals("Size should be 1.", 1, numberOf(Arrays.asList("1")));
        assertEquals("Size should be 3.", 3, numberOf(Arrays.asList("1", "2", "3")));
    }

    @Test
    public void testPrintMap()
    {
        assertEquals("Should be an empty string", "", printMap(null));

        Map<String, String> map = new HashMap<>();
        assertEquals("Should be an empty string", "", printMap(map));

        map.put("1", "One");
        assertEquals("1: One", printMap(map));

        map.put("2", "Two");
        assertThat(printMap(map), CoreMatchers.either(CoreMatchers.is("2: Two; 1: One")).or(CoreMatchers.is("1: One; 2: Two")));
    }
}
