package io.mowitnow.lawn_mower.core.motion;

import io.mowitnow.lawn_mower.core.model.LawnMower;

public interface Motion {
    LawnMower apply(LawnMower current);
}
