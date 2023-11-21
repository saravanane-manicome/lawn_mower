package io.mowitnow.lawn_mower.impl.formatter;

import io.mowitnow.lawn_mower.core.model.LawnMower;
import io.mowitnow.lawn_mower.core.model.MowedLawn;
import io.mowitnow.lawn_mower.core.model.Orientation;
import io.mowitnow.lawn_mower.core.model.Position;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompactMowedLawnFormatterShould {
    private final CompactMowedLawnFormatter formatter = new CompactMowedLawnFormatter();


    @Test
    void returnEmptyListForNoMowers() {
        final MowedLawn mowedLawn = new MowedLawn(0, 0, Collections.emptyList());

        final List<String> formatted = formatter.format(mowedLawn);

        assertEquals(formatted, Collections.emptyList());
    }

    @Test
    void returnLineForOneMower() {
        final List<LawnMower> lawnMowers = List.of(new LawnMower(Position.of(0, 0), Orientation.North));
        final MowedLawn mowedLawn = new MowedLawn(0, 0, lawnMowers);
        final List<String> expectedLines = List.of("0 0 N");

        final List<String> formattedLines = formatter.format(mowedLawn);

        assertEquals(expectedLines, formattedLines);
    }

    @Test
    void returnLinesForSeveralMowers() {
        final List<LawnMower> lawnMowers = List.of(
                new LawnMower(Position.of(0, 0), Orientation.North),
                new LawnMower(Position.of(0, 4), Orientation.East),
                new LawnMower(Position.of(2, 0), Orientation.West),
                new LawnMower(Position.of(3, 5), Orientation.South)
            );
        final MowedLawn mowedLawn = new MowedLawn(0, 0, lawnMowers);
        final List<String> expectedLines = List.of(
                "0 0 N",
                "0 4 E",
                "2 0 W",
                "3 5 S"
        );

        final List<String> formattedLines = formatter.format(mowedLawn);

        assertEquals(expectedLines, formattedLines);
    }
}