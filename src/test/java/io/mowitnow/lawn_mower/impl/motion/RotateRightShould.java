package io.mowitnow.lawn_mower.impl.motion;

import io.mowitnow.lawn_mower.core.model.LawnMower;
import io.mowitnow.lawn_mower.core.model.Orientation;
import io.mowitnow.lawn_mower.core.model.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RotateRightShould {
    private final RotateRight rotateRight = new RotateRight();

    public static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(Orientation.North, Orientation.East),
                Arguments.of(Orientation.East, Orientation.South),
                Arguments.of(Orientation.West, Orientation.North),
                Arguments.of(Orientation.South, Orientation.West)
        );
    }

    @ParameterizedTest()
    @MethodSource("arguments")
    void rotateLawnMowerToTheLeft(Orientation orientation, Orientation expectedOrientation) {
        final LawnMower lawnMower = new LawnMower(Position.of(0, 0), orientation);
        final LawnMower expectedLawnMower = lawnMower.withOrientation(expectedOrientation);

        final LawnMower resultingLawnMower = rotateRight.apply(lawnMower);

        assertEquals(expectedLawnMower, resultingLawnMower);
    }
}