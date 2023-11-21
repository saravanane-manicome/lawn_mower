package io.mowitnow.lawn_mower;

import io.mowitnow.lawn_mower.core.exception.InvalidInputException;
import io.mowitnow.lawn_mower.core.model.MowedLawn;
import io.mowitnow.lawn_mower.core.model.MowingPlan;
import io.mowitnow.lawn_mower.core.parser.MowingPlanParser;
import io.mowitnow.lawn_mower.core.printer.MowedLawnPrinter;
import io.mowitnow.lawn_mower.core.runner.MowingPlanRunner;

public class LawnMowingApplication {
    private final MowingPlanParser parser;
    private final MowingPlanRunner runner;
    private final MowedLawnPrinter printer;

    public LawnMowingApplication(MowingPlanParser parser, MowingPlanRunner runner, MowedLawnPrinter printer) {
        this.parser = parser;
        this.runner = runner;
        this.printer = printer;
    }

    public void process(String filePath) throws InvalidInputException {
        final MowingPlan mowingPlan = parser.parse(filePath);
        final MowedLawn mowedLawn = runner.run(mowingPlan);
        printer.print(mowedLawn);
    }
}
