package io.mowitnow.lawn_mower.impl.formatter;

import io.mowitnow.lawn_mower.core.formatter.MowedLawnFormatter;
import io.mowitnow.lawn_mower.core.model.LawnMower;
import io.mowitnow.lawn_mower.core.model.MowedLawn;
import io.mowitnow.lawn_mower.core.model.Orientation;

import java.util.List;

public class CompactMowedLawnFormatter implements MowedLawnFormatter {
    @Override
    public List<String> format(MowedLawn mowedLawn) {
        return mowedLawn.lawnMowers().stream()
                .map(CompactMowedLawnFormatter::describeLawnMower)
                .toList();
    }

    private static String describeOrientation(Orientation orientation) {
        return switch (orientation) {
            case North -> "N";
            case East -> "E";
            case West -> "W";
            case South -> "S";
        };
    }

    private static String describeLawnMower(LawnMower lawnMower) {
        return lawnMower.position().x()
                + " " + lawnMower.position().y()
                + " " + describeOrientation(lawnMower.orientation());
    }
}
