package com.kanayaya.sort_it.types;

public interface Convertable<T extends Comparable<T>> {
    T convert(String candidate);
}
