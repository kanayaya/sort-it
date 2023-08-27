package com.kanayaya.sort_it.read;

import com.kanayaya.sort_it.sort.Dispencer;
import com.kanayaya.sort_it.sort.Sequencer;

import java.util.Comparator;
import java.util.Iterator;

public class OrderChecker<T extends Comparable<T>> implements Iterable<T> {
    private final Iterable<T> source;
    private final Comparator<T> order;

    public OrderChecker(Iterable<T> source, Comparator<T> comparator) {
        if ( ! source.iterator().hasNext()) throw new IllegalArgumentException("Source should not be empty");
        this.source = source;
        this.order = comparator;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private final Iterator<T> sourceIterator = source.iterator();
            private final Sequencer<T> sequencer = new Sequencer<>(new Dispencer<>(order), sourceIterator.next());
            private T last = null;
            @Override
            public boolean hasNext() {
                if (sourceIterator.hasNext()) return true;
                last = sequencer.apply(null);
                return last != null;
            }

            @Override
            public T next() {
                if (sourceIterator.hasNext()) {
                    T next = sourceIterator.next();
                    T result = sequencer.apply(next);
                    if (result == next) throw new IllegalStateException('"' + result.toString() + "\" is in wrong order");
                    return result;
                }
                return last;
            }
        };
    }
}
