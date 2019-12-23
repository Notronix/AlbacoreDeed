package com.notronix.albacore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.notronix.albacore.Predicates.*;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class PredicatesTest
{
    @Test
    public void testMatchesAll() {
        List<Predicate<String>> predicates = new ArrayList<>();
        predicates.add(Objects::nonNull);

        Predicate<String> predicate = matchesAll(predicates);
        assertTrue(predicate.test(""));

        predicates.add(s -> s.length() == 1);
        predicate = matchesAll(predicates);
        assertTrue(predicate.test("a"));

        predicates.add(s -> s.length() == 2);
        predicate = matchesAll(predicates);
        assertFalse(predicate.test("a"));
    }

    @Test
    public void testMatchesAny() {
        List<Predicate<String>> predicates = new ArrayList<>();
        predicates.add(Objects::isNull);

        Predicate<String> predicate = matchesAny(predicates);
        assertFalse(predicate.test(""));

        predicates.add(Objects::nonNull);
        predicate = matchesAny(predicates);
        assertTrue(predicate.test(""));
    }

    @Test
    public void testMatchesNone() {
        List<Predicate<String>> predicates = new ArrayList<>();
        predicates.add(Objects::isNull);

        Predicate<String> predicate = matchesNone(predicates);
        assertTrue(predicate.test("ab"));

        predicates.add(s -> s.length() == 1);
        predicate = matchesNone(predicates);
        assertTrue(predicate.test("ab"));

        predicates.add(Objects::nonNull);
        predicate = matchesNone(predicates);
        assertFalse(predicate.test("ab"));
    }

    @Test
    public void testRequireNonBlank() {
        assertEquals("test", requireNonBlank("test"));

        try {
            requireNonBlank("  ");
        }
        catch (IllegalArgumentException iae) {
            return;
        }

        fail("should have thrown an exception");
    }

    @Test
    public void testOthers() {
        assertTrue(isTrue(true));
        assertFalse(isTrue(false));

        assertTrue(isTrue("", Objects::nonNull));
        assertFalse(isTrue("", Objects::isNull));

        assertTrue(isFalse(false));
        assertFalse(isFalse(true));

        assertTrue(isFalseOrNull(null));
        assertTrue(isFalseOrNull(false));
        assertFalse(isFalseOrNull(true));

        assertTrue(isTrue("", (Function<String, Boolean>) Objects::nonNull));
        assertFalse(isTrue("", (Function<String, Boolean>) Objects::isNull));
    }
}
