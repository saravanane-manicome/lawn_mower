package io.mowitnow.lawn_mower.impl.printer;

import io.mowitnow.lawn_mower.core.formatter.MowedLawnFormatter;
import io.mowitnow.lawn_mower.core.model.MowedLawn;
import io.mowitnow.lawn_mower.core.printer.MowedLawnPrinter;

import java.io.PrintStream;

public class PrintStreamMowedLawnPrinter implements MowedLawnPrinter {
    private final PrintStream printStream;

    private final MowedLawnFormatter formatter;

    public PrintStreamMowedLawnPrinter(PrintStream printStream, MowedLawnFormatter formatter) {
        this.printStream = printStream;
        this.formatter = formatter;
    }

    @Override
    public void print(MowedLawn mowedLawn) {
        final var lines = formatter.format(mowedLawn);
        lines.forEach(printStream::println);
    }
}
