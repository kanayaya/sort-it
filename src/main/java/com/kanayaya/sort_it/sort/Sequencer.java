package com.kanayaya.sort_it.sort;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Sequencer<T> implements Function<T, T> {
    private final BiFunction<T, T, T> source;
    private T cached;

    public Sequencer(BiFunction<T, T, T> source, T initialValue) {
        this.source = source;
        this.cached = initialValue;
    }

    @Override
    public T apply(T t) {
        if (t == null) return cached;
        if (source.apply(cached, t) == t) {
            T result = cached;
            cached = t;
            return result;
        }
        return t;
    }
}
