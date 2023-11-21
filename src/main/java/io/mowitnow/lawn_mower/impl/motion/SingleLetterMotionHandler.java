package io.mowitnow.lawn_mower.impl.motion;

import io.mowitnow.lawn_mower.core.motion.Motion;
import io.mowitnow.lawn_mower.core.motion.MotionHandler;

import java.util.Map;
import java.util.Optional;

public class SingleLetterMotionHandler implements MotionHandler {
    private final Map<String, Motion> motionsByKey = Map.of(
            "A", new Forward(),
            "G", new RotateLeft(),
            "D", new RotateRight()
    );

    @Override
    public Optional<Motion> getMotion(String key) {
        return Optional.ofNullable(motionsByKey.get(key));
    }
}
