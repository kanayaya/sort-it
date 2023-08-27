package com.kanayaya.sort_it.types;

public class CheckedInteger implements Convertable<Integer> {
    @Override
    public Integer convert(String candidate) {
        try {
            return Integer.parseInt(candidate);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Could not convert to integer: " + candidate);
        }
    }
}
