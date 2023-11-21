package io.mowitnow.lawn_mower.core.motion;

import java.util.Optional;

public interface MotionHandler {
    Optional<Motion> getMotion(String key);
}
