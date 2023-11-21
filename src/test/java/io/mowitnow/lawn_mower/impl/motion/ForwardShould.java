package io.mowitnow.lawn_mower.impl.motion;

import io.mowitnow.lawn_mower.core.model.LawnMower;
import io.mowitnow.lawn_mower.core.model.Orientation;
import io.mowitnow.lawn_mower.core.model.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ForwardShould {
    private final Forward forward = new Forward();

    public static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(Orientation.North, Position.of(0, 1)),
                Arguments.of(Orientation.East, Position.of(1, 0)),
                Arguments.of(Orientation.West, Position.of(-1, 0)),
                Arguments.of(Orientation.South, Position.of(0, -1))
        );
    }

    @ParameterizedTest()
    @MethodSource("arguments")
    void moveLawnMowerForward(Orientation orientation, Position expectedPosition) {
        final LawnMower lawnMower = new LawnMower(Position.of(0, 0), orientation);
        final LawnMower expectedLawnMower = lawnMower.withPosition(expectedPosition);

        final LawnMower resultingLawnMower = forward.apply(lawnMower);

        assertEquals(expectedLawnMower, resultingLawnMower);
    }
}