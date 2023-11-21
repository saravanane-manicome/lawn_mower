package io.mowitnow.lawn_mower.impl.motion;

import io.mowitnow.lawn_mower.core.model.LawnMower;
import io.mowitnow.lawn_mower.core.model.Orientation;
import io.mowitnow.lawn_mower.core.motion.Motion;

public class RotateRight implements Motion {
    @Override
    public LawnMower apply(LawnMower current) {
        return switch (current.orientation()) {
            case North -> current.withOrientation(Orientation.East);
            case East -> current.withOrientation(Orientation.South);
            case West -> current.withOrientation(Orientation.North);
            case South -> current.withOrientation(Orientation.West);
        };
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof RotateRight;
    }
}
