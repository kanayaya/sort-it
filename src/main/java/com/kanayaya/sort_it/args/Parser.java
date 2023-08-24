package com.kanayaya.sort_it.args;

import com.kanayaya.sort_it.types.CheckedInteger;
import com.kanayaya.sort_it.types.Convertable;
import com.kanayaya.sort_it.types.NoSpaceString;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;

public class Parser {
    private static final Map<String, Consumer<Parser>> types = new HashMap<>();
    static {
        types.put("-s", x -> setChecked(x, new NoSpaceString()));
        types.put("-i", x -> setChecked(x, new CheckedInteger()));
        types.put("-d", x -> setChecked(x, Comparator.reverseOrder()));
        types.put("-a", x -> setChecked(x, Comparator.naturalOrder()));
    }

    private static void setChecked(Parser x, Comparator<? extends Comparable<?>> order) {
        if (x.order == null) x.order = order;
        else System.out.println("Order has already been set. Continuing with first one");
    }

    private static void setChecked(Parser x, Convertable<?> convertable) {
        if (x.type == null) x.type = convertable;
        else System.out.println("Data type has already been set. Continuing with first one");
    }

    private Comparator<? extends Comparable<?>> order;
    private final List<String> sourcePaths = new ArrayList<>();
    private final String destination;
    private Convertable<?> type;

    public Parser(String[] args) {
        List<String> strings = Arrays.asList(args);
        for (Iterator<String> iterator = strings.iterator(); iterator.hasNext(); ) {
            String arg = iterator.next();
            if (arg.startsWith("-")) {
                Optional.of(types.get(arg))
                        .orElse(x -> System.out.println("Invalid argument: '" + arg + "' in " + x))
                        .accept(this);
                iterator.remove();
            }
        }
        if (isValidPath(strings.get(0))) {
            System.out.println("Destination: " + strings.get(0));
            destination = strings.get(0);
        } else throw new IllegalArgumentException("First path should be valid but got: " + strings.get(0));

        for (int i = 1; i < strings.size(); i++) {
            if (isValidPath(strings.get(i))) sourcePaths.add(strings.get(i));
            else System.out.println("Invalid data source path: " + strings.get(i));
        }
        if (sourcePaths.isEmpty()) throw new IllegalArgumentException("At least one source path should be specified");
    }

    private boolean isValidPath(String path) {
        try {
            Paths.get(path);
            return true;
        } catch (InvalidPathException e) {
            return false;
        }
    }

    public Comparator<? extends Comparable<?>> getOrder() {
        return order;
    }

    public List<String> getSourcePaths() {
        return sourcePaths;
    }

    public String getDestination() {
        return destination;
    }

    public Convertable<?> getType() {
        return type;
    }
}
