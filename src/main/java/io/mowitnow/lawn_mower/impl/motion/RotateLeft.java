package io.mowitnow.lawn_mower.impl.motion;

import io.mowitnow.lawn_mower.core.model.LawnMower;
import io.mowitnow.lawn_mower.core.model.Orientation;
import io.mowitnow.lawn_mower.core.motion.Motion;

public class RotateLeft implements Motion {
    @Override
    public LawnMower apply(LawnMower current) {
        return switch (current.orientation()) {
            case North -> current.withOrientation(Orientation.West);
            case East -> current.withOrientation(Orientation.North);
            case West -> current.withOrientation(Orientation.South);
            case South -> current.withOrientation(Orientation.East);
        };
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof RotateLeft;
    }
}
