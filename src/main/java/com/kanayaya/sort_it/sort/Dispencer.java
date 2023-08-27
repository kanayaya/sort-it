package com.kanayaya.sort_it.sort;

import java.util.Comparator;
import java.util.function.BiFunction;

public class Dispencer<T extends Comparable<? super T>> implements BiFunction<T, T, T> {
    private final Comparator<T> comparator;

    public Dispencer(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public T apply(T t, T t2) {
        if (comparator.compare(t, t2) > 0) {
            return t;
        }
        return t2;
    }
}
