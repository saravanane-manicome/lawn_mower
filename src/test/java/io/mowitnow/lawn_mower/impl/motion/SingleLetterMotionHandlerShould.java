package io.mowitnow.lawn_mower.impl.motion;

import io.mowitnow.lawn_mower.core.motion.Motion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SingleLetterMotionHandlerShould {
    private final SingleLetterMotionHandler motionHandler = new SingleLetterMotionHandler();

    public static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of("A", new Forward()),
                Arguments.of("G", new RotateLeft()),
                Arguments.of("D", new RotateRight())
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void returnMotionGivenRegisteredToken(String token, Motion expectedMotion) {
        final Motion motion = assertDoesNotThrow(() -> motionHandler.getMotion(token).orElseThrow());
        assertEquals(expectedMotion, motion);
    }

    @Test
    void returnEmptyGivenUnknownToken() {
        assertEquals(Optional.empty(), motionHandler.getMotion("☀️"));
    }
}