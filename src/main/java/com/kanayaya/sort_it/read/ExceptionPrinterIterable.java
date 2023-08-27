package com.kanayaya.sort_it.read;

import java.util.Iterator;

public class ExceptionPrinterIterable<T extends Comparable<T>> implements Iterable<T> {
    private final Iterable<T> source;

    public ExceptionPrinterIterable(Iterable<T> source) {
        this.source = source;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private final Iterator<T> it = source.iterator();
            private T next = getNext();

            @Override
            public boolean hasNext() {
                return next != null;
            }

            @Override
            public T next() {
                T result = next;
                next = getNext();
                return result;
            }
            private T getNext() {
                while (it.hasNext()) {
                    try {
                        return it.next();
                    } catch (IllegalStateException | IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                return null;
            }
        };
    }
}
