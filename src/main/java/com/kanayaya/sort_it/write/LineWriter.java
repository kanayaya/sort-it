package com.kanayaya.sort_it.write;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LineWriter implements AutoCloseable{
    private final BufferedWriter writer;

    public LineWriter(String path) throws IOException {
        this.writer = new BufferedWriter(new FileWriter(path, true));
    }

    public boolean writeLine(String line) {
        try {
            writer.write(line);
            writer.newLine();
            System.out.println("wrote: " + line);
            return true;
        } catch (IOException e) {
            System.out.println(writer);
            return false;
        }
    }

    @Override
    public void close() throws Exception {
        System.out.println("closed");
        writer.close();
    }
}
