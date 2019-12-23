package com.notronix.albacore;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.notronix.albacore.Optionals.*;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.Collections.singletonMap;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class OptionalsTest
{
    @Test
    public void testOfBlankable() {
        AtomicBoolean ran = new AtomicBoolean(false);

        ofBlankable(null).ifPresent(value -> ran.set(true));
        ofBlankable("").ifPresent(value -> ran.set(true));
        ofBlankable("   ").ifPresent(value -> ran.set(true));
        assertFalse("items should have been considered blank", ran.get());

        ofBlankable("a").ifPresent(value -> ran.set(true));
        assertTrue("item should not be considered blank", ran.get());
    }

    @Test
    public void testOfEmptiable() {
        AtomicBoolean ran = new AtomicBoolean(false);

        ofEmptiable((Collection<?>) null).ifPresent(value -> ran.set(true));
        ofEmptiable((Map<?, ?>) null).ifPresent(value -> ran.set(true));
        ofEmptiable(new ArrayList<>()).ifPresent(value -> ran.set(true));
        ofEmptiable(new HashMap<>()).ifPresent(value -> ran.set(true));
        assertFalse("items should have been considered empty", ran.get());

        ofEmptiable(singletonList("a")).ifPresent(value -> ran.set(true));
        assertTrue("item should not be considered empty", ran.get());

        ran.set(false);
        ofEmptiable(singletonMap("a", null)).ifPresent(value -> ran.set(true));
        assertTrue("item should not be considered empty", ran.get());
    }

    @Test
    public void testOfPredicate() {
        AtomicBoolean ran = new AtomicBoolean(false);

        ofPredicate("", StringUtils::isBlank).ifPresent(value -> ran.set(true));
        assertFalse("nothing should have run", ran.get());

        ofPredicate("", StringUtils::isNotBlank).ifPresent(value -> ran.set(true));
        assertTrue("should have run", ran.get());
    }

    @Test
    public void testSetIfChanged() {
        AtomicBoolean changed = new AtomicBoolean(false);
        List<String> list = new ArrayList<>(asList("1", "2"));

        setIfChanged(list, l -> l.get(0), "1", (l, v) -> l.set(0, v), changed);
        assertFalse("item should not have been changed", changed.get());

        setIfChanged(list, l -> l.get(0), "2", (l, v) -> l.set(0, v), changed);
        assertTrue("item should have been changed", changed.get());
    }
}
