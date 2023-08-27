package com.kanayaya;

import com.kanayaya.sort_it.args.Parser;
import com.kanayaya.sort_it.read.ExceptionPrinterIterable;
import com.kanayaya.sort_it.read.IterableFile;
import com.kanayaya.sort_it.read.OrderChecker;
import com.kanayaya.sort_it.sort.MergingIterable;
import com.kanayaya.sort_it.types.CheckedInteger;
import com.kanayaya.sort_it.types.ConvertIterable;
import com.kanayaya.sort_it.types.Convertable;
import com.kanayaya.sort_it.types.NoSpaceString;
import com.kanayaya.sort_it.write.LineWriter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try {
            Parser parser = new Parser(args);
            chooseType(parser); // then choose order and start!
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void chooseType(Parser parser) {
        switch (parser.getType()) {
            case CHECKED_INTEGER: {
                chooseOrder(parser, new CheckedInteger());
                break;
            }
            case NO_SPACE_STRING: {
                chooseOrder(parser, new NoSpaceString());
                break;
            }
        }
    }

    private static <T extends Comparable<? super T>> void chooseOrder(Parser parser, Convertable<T> convertable) {
        switch (parser.getOrder()) {
            case NATURAL: {
                readMergeAndWrite(parser.getDestination(), Comparator.naturalOrder(), convertable, parser.getSourcePaths());
                break;
            }
            case REVERSE: {
                readMergeAndWrite(parser.getDestination(), Comparator.reverseOrder(), convertable, parser.getSourcePaths());
                break;
            }
        }
    }

    private static <T extends Comparable<? super T>> void readMergeAndWrite(String destination, Comparator<T> comparator, Convertable<T> convertable, List<String> sourcePaths) {
        MergingIterable<T> result = new MergingIterable<>(
                    sourcePaths.stream().map(path ->
                                    new ExceptionPrinterIterable<>(
                                            new OrderChecker<>(
                                                    new ConvertIterable<>(
                                                            convertable,
                                                            new IterableFile(path)),
                                                    comparator)))
                            .collect(Collectors.toList()),
                comparator
            );

        try (LineWriter writer = new LineWriter(destination)){
            result.forEach(line -> writer.writeLine(line.toString()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}