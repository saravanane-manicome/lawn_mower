package io.mowitnow.lawn_mower.impl.sequential_runner;

import io.mowitnow.lawn_mower.core.model.*;
import io.mowitnow.lawn_mower.core.motion.Motion;

import java.util.List;

final class SequentialMowingPlanExecutor {
    private final MowingPlan mowingPlan;
    private List<Position> lawnMowerPositions;

    SequentialMowingPlanExecutor(MowingPlan mowingPlan) {
        this.mowingPlan = mowingPlan;
        this.lawnMowerPositions = mowingPlan.lawnMowerPrograms().stream()
                .map(LawnMowerProgram::lawnMower)
                .map(LawnMower::position)
                .toList();
    }


    private LawnMower validateNewPosition(LawnMower previousLawnMower, LawnMower newLawnMower) {
        if((newLawnMower.position().x() < 0) || (newLawnMower.position().x() > mowingPlan.width())) {
            return previousLawnMower;
        }

        if((newLawnMower.position().y() < 0) || (newLawnMower.position().y() > mowingPlan.height())) {
            return previousLawnMower;
        }

        final boolean newLawnMowerIsColliding = lawnMowerPositions.stream()
                .filter(position -> !position.equals(previousLawnMower.position()))
                .anyMatch(newLawnMower.position()::equals);

        if(newLawnMowerIsColliding) {
            return previousLawnMower;
        }

        return newLawnMower;
    }

    private void updateWithNewLawnMower(LawnMower previousLawnMower, LawnMower newLawnMower) {
        lawnMowerPositions = lawnMowerPositions.stream()
                .map(position -> previousLawnMower.position().equals(position) ? newLawnMower.position() : position)
                .toList();
    }

    private LawnMower moveLawnMower(LawnMowerProgram lawnMowerProgram) {
        LawnMower lawnMower = lawnMowerProgram.lawnMower();

        for(Motion motion: lawnMowerProgram.motions()) {
            LawnMower newLawnMower = motion.apply(lawnMower);
            newLawnMower = validateNewPosition(lawnMower, newLawnMower);

            updateWithNewLawnMower(lawnMower, newLawnMower);

            lawnMower = newLawnMower;
        }

        return lawnMower;
    }

    MowedLawn execute() {
        final List<LawnMower> lawnMowers = mowingPlan.lawnMowerPrograms().stream()
                .map(this::moveLawnMower)
                .toList();

        return new MowedLawn(mowingPlan.width(), mowingPlan.height(), lawnMowers);
    }
}
