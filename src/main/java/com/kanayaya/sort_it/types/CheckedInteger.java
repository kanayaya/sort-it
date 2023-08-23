package com.kanayaya.sort_it.types;

public class CheckedInteger implements Convertable<Integer> {
    @Override
    public Integer convert(String candidate) {
        return Integer.parseInt(candidate);
    }
}
