package io.mowitnow.lawn_mower.impl.sequential_runner;

import io.mowitnow.lawn_mower.core.model.MowedLawn;
import io.mowitnow.lawn_mower.core.model.MowingPlan;
import io.mowitnow.lawn_mower.core.runner.MowingPlanRunner;

public class SequentialMowingPlanRunner implements MowingPlanRunner {
    public MowedLawn run(MowingPlan mowingPlan) {
        return new SequentialMowingPlanExecutor(mowingPlan).execute();
    }
}
