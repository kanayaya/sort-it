package com.kanayaya.sort_it.sort;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class MergingIterable<T extends Comparable<T>> implements Iterable<T> {
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

            private final List<T> values = iterators.stream()
                    .map(Iterator::next)
                    .collect(Collectors.toList());

            private final Sequencer<T> sequencer = new Sequencer<>(new Dispencer<>(comparator), getClosest());

            @Override
            public boolean hasNext() {
                return iterators.stream().anyMatch(Iterator::hasNext);
            }

            @Override
            public T next() {
                while (hasNext()) {
                    T closest = getClosest();
                    T apply = sequencer.apply(closest);
                    if (apply != closest) return apply;
                }
                return null;
            }

            private T getClosest() {
                T result = values.get(0);
                int index = 0;
                for (int i = 1; i < iterators.size(); i++) {
                    T val = values.get(i);
                    if (val != null) {
                        if (val.compareTo(result) < 0) {
                            result = val;
                            index = i;
                        }
                    }
                }
                values.remove(index);
                T next = iterators.get(index).hasNext()? iterators.get(index).next(): null;
                values.add(index, next);
                return result;
            }
        };
    }
}
