package io.mowitnow.lawn_mower.impl.motion;

import io.mowitnow.lawn_mower.core.model.LawnMower;
import io.mowitnow.lawn_mower.core.model.Orientation;
import io.mowitnow.lawn_mower.core.model.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RotateLeftShould {
    private final RotateLeft rotateLeft = new RotateLeft();

    public static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(Orientation.North, Orientation.West),
                Arguments.of(Orientation.East, Orientation.North),
                Arguments.of(Orientation.West, Orientation.South),
                Arguments.of(Orientation.South, Orientation.East)
        );
    }

    @ParameterizedTest()
    @MethodSource("arguments")
    void rotateLawnMowerToTheLeft(Orientation orientation, Orientation expectedOrientation) {
        final LawnMower lawnMower = new LawnMower(Position.of(0, 0), orientation);
        final LawnMower expectedLawnMower = lawnMower.withOrientation(expectedOrientation);

        final LawnMower resultingLawnMower = rotateLeft.apply(lawnMower);

        assertEquals(expectedLawnMower, resultingLawnMower);
    }
}