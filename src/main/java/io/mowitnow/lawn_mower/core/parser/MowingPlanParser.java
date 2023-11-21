package io.mowitnow.lawn_mower.core.parser;

import io.mowitnow.lawn_mower.core.exception.InvalidInputException;
import io.mowitnow.lawn_mower.core.model.MowingPlan;

public interface MowingPlanParser {
    MowingPlan parse(String filepath) throws InvalidInputException;
}
