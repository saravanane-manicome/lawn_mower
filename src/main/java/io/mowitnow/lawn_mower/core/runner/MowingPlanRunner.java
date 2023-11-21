package io.mowitnow.lawn_mower.core.runner;

import io.mowitnow.lawn_mower.core.model.MowedLawn;
import io.mowitnow.lawn_mower.core.model.MowingPlan;

public interface MowingPlanRunner {
    MowedLawn run(MowingPlan mowingPlan);
}
