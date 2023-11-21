package io.mowitnow.lawn_mower;

import io.mowitnow.lawn_mower.core.exception.InvalidInputException;
import io.mowitnow.lawn_mower.impl.flat_file_parser.LineSeparatedMowingPlanParser;
import io.mowitnow.lawn_mower.impl.formatter.CompactMowedLawnFormatter;
import io.mowitnow.lawn_mower.impl.motion.SingleLetterMotionHandler;
import io.mowitnow.lawn_mower.impl.printer.PrintStreamMowedLawnPrinter;
import io.mowitnow.lawn_mower.impl.sequential_runner.SequentialMowingPlanRunner;

public final class LineBasedApplication {
    private static final LawnMowingApplication lawnMowingApplication;
    static {
        final SingleLetterMotionHandler motionHandler = new SingleLetterMotionHandler();

        final LineSeparatedMowingPlanParser lawnParser = new LineSeparatedMowingPlanParser(motionHandler);

        final SequentialMowingPlanRunner mowingPlanRunner = new SequentialMowingPlanRunner();

        final CompactMowedLawnFormatter mowedLawnFormatter = new CompactMowedLawnFormatter();
        final PrintStreamMowedLawnPrinter mowedLawnPrinter = new PrintStreamMowedLawnPrinter(System.out, mowedLawnFormatter);

        lawnMowingApplication = new LawnMowingApplication(lawnParser, mowingPlanRunner, mowedLawnPrinter);
    }

    private LineBasedApplication() {}

    public static void run(String filepath) throws InvalidInputException {
        lawnMowingApplication.process(filepath);
    }
}
