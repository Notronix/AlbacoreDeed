package com.notronix.functional;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

public abstract class Predicates
{
    private Predicates() {
    }

    public static <T> Predicate<T> matchesAll(Collection<Predicate<T>> predicates) {
        return t -> {
            for (Predicate<T> predicate : predicates) {
                if (!predicate.test(t)) {
                    return false;
                }
            }

            return true;
        };
    }

    public static <T> Predicate<T> matchesAny(Collection<Predicate<T>> predicates) {
        return t -> {
            for (Predicate<T> predicate : predicates) {
                if (predicate.test(t)) {
                    return true;
                }
            }

            return false;
        };
    }

    public static <T> Predicate<T> matchesNone(List<Predicate<T>> predicates) {
        return t -> {
            for (Predicate<? super T> predicate : predicates) {
                if (predicate.test(t)) {
                    return false;
                }
            }

            return true;
        };
    }

    public static <T> boolean isTrue(T object, Predicate<T> predicate) {
        return requireNonNull(predicate).test(object);
    }

    public static boolean isTrue(final Boolean bool) {
        return isTrue(bool, TRUE::equals);
    }

    public static <T> boolean isTrue(T object, final Function<T, Boolean> producer) {
        return isTrue(requireNonNull(producer).apply(object));
    }

    public static boolean isFalse(final Boolean bool) {
        return isTrue(bool, FALSE::equals);
    }

    public static boolean isFalseOrNull(final Boolean bool) {
        return bool == null || isFalse(bool);
    }

    public static <T extends CharSequence> T requireNonBlank(T string) throws IllegalArgumentException {
        if (isBlank(string)) {
            throw new IllegalArgumentException("value is blank");
        }

        return string;
    }
}
