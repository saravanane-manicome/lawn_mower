package io.mowitnow.lawn_mower.impl.sequential_runner;

import io.mowitnow.lawn_mower.core.model.*;
import io.mowitnow.lawn_mower.core.motion.Motion;
import io.mowitnow.lawn_mower.impl.motion.Forward;
import io.mowitnow.lawn_mower.impl.motion.RotateLeft;
import io.mowitnow.lawn_mower.impl.motion.RotateRight;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SequentialMowingPlanExecutorShould {
    private static final Motion ROTATE_LEFT = new RotateLeft();
    private static final Motion ROTATE_RIGHT = new RotateRight();
    private static final Motion FORWARD = new Forward();

    private static MowingPlan mowingPlan;

    @BeforeAll
    public static void setup() {
        mowingPlan = new MowingPlan.MowingPlanBuilder()
                .setWidth(5)
                .setHeight(5)
                .addLawnMowerWithMotions(
                        new LawnMower(Position.of(1, 2), Orientation.North),
                        List.of(
                                ROTATE_LEFT, FORWARD, ROTATE_LEFT,
                                FORWARD, ROTATE_LEFT, FORWARD,
                                ROTATE_LEFT, FORWARD, FORWARD
                        )
                )
                .addLawnMowerWithMotions(
                        new LawnMower(Position.of(3, 3), Orientation.East),
                        List.of(
                                FORWARD, FORWARD, ROTATE_RIGHT, FORWARD, FORWARD,
                                ROTATE_RIGHT, FORWARD, ROTATE_RIGHT, ROTATE_RIGHT, FORWARD
                        )
                )
                .build();
    }

    @Test
    void shouldExecuteMowingPlan() {
        final MowedLawn expectedMowedLawn = new MowedLawn(5, 5, List.of(
                new LawnMower(Position.of(1, 3), Orientation.North),
                new LawnMower(Position.of(5, 1), Orientation.East)
        ));

        final SequentialMowingPlanExecutor executor = new SequentialMowingPlanExecutor(mowingPlan);
        final MowedLawn mowedLawn = executor.execute();

        assertEquals(expectedMowedLawn, mowedLawn);
    }
}