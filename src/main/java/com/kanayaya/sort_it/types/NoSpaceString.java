package com.kanayaya.sort_it.types;

public class NoSpaceString implements Convertable<String> {
    @Override
    public String convert(String candidate) {
        if (candidate.contains(" ")) {
            throw new IllegalArgumentException('"' + candidate + '"' + " should not contain spaces");
        }
        return candidate;
    }
}
