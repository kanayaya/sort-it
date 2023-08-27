package com.kanayaya.sort_it.types;

import java.util.Iterator;

public class ConvertIterable<T extends Comparable<T>> implements Iterable<T> {
    private final Convertable<T> convertable;
    private final Iterable<String> values;

    public ConvertIterable(Convertable<T> convertable, Iterable<String> values) {
        this.convertable = convertable;
        this.values = values;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            final Iterator<String> iterator = values.iterator();
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public T next() {
                return convertable.convert(iterator.next());
            }
        };
    }
}
