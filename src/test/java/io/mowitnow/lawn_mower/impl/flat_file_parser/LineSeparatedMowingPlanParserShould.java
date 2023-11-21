package io.mowitnow.lawn_mower.impl.flat_file_parser;

import io.mowitnow.lawn_mower.core.exception.InvalidInputException;
import io.mowitnow.lawn_mower.core.model.LawnMower;
import io.mowitnow.lawn_mower.core.model.MowingPlan;
import io.mowitnow.lawn_mower.core.model.Orientation;
import io.mowitnow.lawn_mower.core.model.Position;
import io.mowitnow.lawn_mower.core.motion.Motion;
import io.mowitnow.lawn_mower.core.motion.MotionHandler;
import io.mowitnow.lawn_mower.impl.motion.Forward;
import io.mowitnow.lawn_mower.impl.motion.RotateLeft;
import io.mowitnow.lawn_mower.impl.motion.RotateRight;
import io.mowitnow.lawn_mower.impl.motion.SingleLetterMotionHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LineSeparatedMowingPlanParserShould {
    private static final Motion ROTATE_LEFT = new RotateLeft();
    private static final Motion ROTATE_RIGHT = new RotateRight();
    private static final Motion FORWARD = new Forward();
    private static MowingPlan mowingPlan;

    private final MotionHandler motionHandler = new SingleLetterMotionHandler();

    private final LineSeparatedMowingPlanParser mowingPlanParser = new LineSeparatedMowingPlanParser(motionHandler);


    @BeforeAll
    public static void setup() {
        final LawnMower lawnMower1 = new LawnMower(Position.of(1, 2), Orientation.North);
        final List<Motion> lawnMower1Motions = List.of(
                ROTATE_LEFT, FORWARD, ROTATE_LEFT,
                FORWARD, ROTATE_LEFT, FORWARD,
                ROTATE_LEFT, FORWARD, FORWARD
        );

        final LawnMower lawnMower2 = new LawnMower(Position.of(3, 3), Orientation.East);
        final List<Motion> lawnMower2Motions = List.of(
                FORWARD, FORWARD, ROTATE_RIGHT, FORWARD, FORWARD,
                ROTATE_RIGHT, FORWARD, ROTATE_RIGHT, ROTATE_RIGHT, FORWARD
        );

        mowingPlan = new MowingPlan.MowingPlanBuilder()
                .setWidth(5)
                .setHeight(5)
                .addLawnMowerWithMotions(lawnMower1, lawnMower1Motions)
                .addLawnMowerWithMotions(lawnMower2, lawnMower2Motions)
                .build();
    }

    @Test
    void parseMowingPlan() throws InvalidInputException {
        final MowingPlan resultingMowingPlan = mowingPlanParser.parse("src/test/resources/valid.txt");

        assertEquals(mowingPlan, resultingMowingPlan);
    }

    @Test
    void thrownOnInvalidLawnSizeFormat() {
        assertThrows(InvalidInputException.class, () -> mowingPlanParser.parse("src/test/resources/invalid_lawn_size.txt"));
    }

    @Test
    void thrownOnInvalidPositionFormat() {
        assertThrows(InvalidInputException.class, () -> mowingPlanParser.parse("src/test/resources/invalid_position.txt"));
    }

    @Test
    void thrownOnInvalidOrientationFormat() {
        assertThrows(InvalidInputException.class, () -> mowingPlanParser.parse("src/test/resources/invalid_orientation.txt"));
    }

    @Test
    void thrownOnInvalidLawnMowerFormat() {
        assertThrows(InvalidInputException.class, () -> mowingPlanParser.parse("src/test/resources/invalid_lawn_mower.txt"));
    }

    @Test
    void thrownOnInvalidMotionFormat() {
        assertThrows(InvalidInputException.class, () -> mowingPlanParser.parse("src/test/resources/invalid_motion.txt"));
    }

    @Test
    void thrownOnInvalidMotionSequenceFormat() {
        assertThrows(InvalidInputException.class, () -> mowingPlanParser.parse("src/test/resources/invalid_motion_sequence.txt"));
    }
}