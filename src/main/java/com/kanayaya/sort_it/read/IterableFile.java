package com.kanayaya.sort_it.read;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class IterableFile implements Iterable<String> {
    private final String path;

    public IterableFile(String path) {
        this.path = path;
    }

    @Override
    public Iterator<String> iterator() {
        return new FileIterator(path);
    }

    private static final class FileIterator implements Iterator<String> {
        private final BufferedReader reader;
        private String next;

        private FileIterator(String path) {
            try {
                this.reader = new BufferedReader(new FileReader(path));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            try {
                this.next = reader.readLine();
            } catch (IOException e) {
                this.next = null;
            }
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public String next() {
            if (next == null) {return null;}
            String result = next;
            try {
                next = reader.readLine();
            } catch (IOException e) {
                next = null;
            }
            return result;
        }
    }
}