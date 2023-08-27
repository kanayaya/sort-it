package com.kanayaya.sort_it.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class MergingIterable<T extends Comparable<? super T>> implements Iterable<T> {
    private final List<Iterable<T>> sources;
    private final Comparator<T> comparator;

    public MergingIterable(List<Iterable<T>> sources, Comparator<T> comparator) {
        this.sources = sources;
        this.comparator = comparator;
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private final List<Iterator<T>> iterators = sources.stream()
                    .map(Iterable::iterator)
                    .filter(Iterator::hasNext)
                    .collect(Collectors.toList());

            private final List<T> values;
            {
                List<T> list = new ArrayList<>();
                for (Iterator<T> iterator : iterators) {
                    T next = null;
                    while (next == null) {
                        next = iterator.next();
                    }
                    list.add(next);
                }
                values = list;
            }
            private T next = getClosest();
            @Override
            public boolean hasNext() {
                return next != null;
            }

            @Override
            public T next() {
                T result = next;
                next = getClosest();
                return result;
            }

            private T getClosest() {
                if (iterators.isEmpty() || values.isEmpty()) {return null;}
                T result = null;
                int index = 0;
                for (int i = 0; i < iterators.size(); i++) {
                    if (values.get(i) != null) {
                        T value = values.get(i);
                        if (result == null || comparator.compare(value, result) < 0) {
                            result = value;
                            index = i;
                        }
                    }
                }
                boolean changerHasNext = iterators.get(index).hasNext();
                T next = changerHasNext ? iterators.get(index).next() : null;
                values.set(index, next);
                return result;
            }

        };
    }
}
