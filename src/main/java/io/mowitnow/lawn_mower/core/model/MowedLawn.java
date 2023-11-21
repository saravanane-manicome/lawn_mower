package io.mowitnow.lawn_mower.core.model;

import java.util.Collections;
import java.util.List;

public record MowedLawn(int width, int height, List<LawnMower> lawnMowers) {
    public static MowedLawn of(int width, int height, List<LawnMower> lawnMowers) {
        return new MowedLawn(width, height, Collections.unmodifiableList(lawnMowers));
    }
}
