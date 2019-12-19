package com.notronix.albacore;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.notronix.albacore.ContainerUtils.thereAreNo;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.apache.commons.lang3.StringUtils.isBlank;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
public abstract class Optionals
{
    private Optionals() {
    }

    public static java.util.Optional<String> ofBlankable(String value) {
        return isBlank(value) ? empty() : of(value);
    }

    public static <T> java.util.Optional<List<T>> ofEmptiable(List<T> value) {
        return thereAreNo(value) ? empty() : of(value);
    }

    @Deprecated() // use Optional<T> filter(Predicate<? super T> predicate)
    public static <T> java.util.Optional<T> ofPredicate(T value, Predicate<T> predicate) {
        return predicate.test(value) ? empty() : of(value);
    }

    public static <K, V> java.util.Optional<Map<K, V>> ofEmptiable(Map<K, V> value) {
        return thereAreNo(value) ? empty() : of(value);
    }

    public static <T, R> void setIfChanged(T valueHolder,
                                           Function<T, R> valueFunction,
                                           R newValue,
                                           BiConsumer<T, R> action,
                                           AtomicBoolean flag) {
        if (!Objects.equals(valueFunction.apply(valueHolder), newValue)) {
            action.accept(valueHolder, newValue);
            flag.set(true);
        }
    }
}
