package com.kanayaya.sort_it.write;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LineWriter implements AutoCloseable{
    private final BufferedWriter writer;

    public LineWriter(String path) throws IOException {
        this.writer = new BufferedWriter(new FileWriter(path, false));
    }

    public boolean writeLine(String line) {
        try {
            writer.write(line);
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Did not write line: '" + line + "' error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void close() throws Exception {
        writer.close();
    }
}
