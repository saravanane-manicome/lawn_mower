package io.mowitnow.lawn_mower.impl.motion;

import io.mowitnow.lawn_mower.core.model.LawnMower;
import io.mowitnow.lawn_mower.core.motion.Motion;

public class Forward implements Motion {
    @Override
    public LawnMower apply(LawnMower current) {
        return switch (current.orientation()) {
            case North -> current.withPosition(current.position().withY(current.position().y() + 1));
            case East -> current.withPosition(current.position().withX(current.position().x() + 1));
            case West -> current.withPosition(current.position().withX(current.position().x() - 1));
            case South -> current.withPosition(current.position().withY(current.position().y() - 1));
        };
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Forward;
    }
}
