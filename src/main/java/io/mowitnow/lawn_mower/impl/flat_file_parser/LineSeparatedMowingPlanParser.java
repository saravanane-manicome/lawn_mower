package io.mowitnow.lawn_mower.impl.flat_file_parser;

import io.mowitnow.lawn_mower.core.exception.InvalidInputException;
import io.mowitnow.lawn_mower.core.model.LawnMower;
import io.mowitnow.lawn_mower.core.model.MowingPlan;
import io.mowitnow.lawn_mower.core.model.Orientation;
import io.mowitnow.lawn_mower.core.model.Position;
import io.mowitnow.lawn_mower.core.motion.Motion;
import io.mowitnow.lawn_mower.core.motion.MotionHandler;
import io.mowitnow.lawn_mower.core.parser.MowingPlanParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class LineSeparatedMowingPlanParser implements MowingPlanParser {
    private static final Pattern lawnMowerRegex = Pattern.compile("^\\d+ \\d+ [NEWS]$");
    private static final Pattern positionRegex = Pattern.compile("^[\\d+] [\\d+]$");

    private final MotionHandler motionHandler;

    public LineSeparatedMowingPlanParser(MotionHandler motionHandler) {
        this.motionHandler = motionHandler;
    }

    private Orientation parseOrientation(String input) throws InvalidInputException {
        return switch (input) {
            case "N" -> Orientation.North;
            case "E" -> Orientation.East;
            case "W" -> Orientation.West;
            case "S" -> Orientation.South;
            case null, default -> throw new InvalidInputException("invalid input for orientation: " + input);
        };
    }

    private Motion parseMotion(String motionString) throws InvalidInputException {
        return motionHandler.getMotion(motionString)
                .orElseThrow(() -> new InvalidInputException("invalid input for motion: " + motionString));
    }

    private List<Motion> parseMotionSequence(String input) throws InvalidInputException {
        if(input.isBlank()) return Collections.emptyList();

        final List<Motion> motions = new ArrayList<>();


        for(String token : input.split("")) {
            motions.add(parseMotion(token));
        }

        return Collections.unmodifiableList(motions);
    }

    private Position parsePosition(String input) throws InvalidInputException {
        if(!positionRegex.matcher(input).matches()) throw new InvalidInputException("unrecognized position");

        final String[] coordinates = input.split(" ");
        final int width = Integer.parseInt(coordinates[0]);
        final int height = Integer.parseInt(coordinates[1]);

        return Position.of(width, height);
    }

    private LawnMower parseLawnMower(String input) throws InvalidInputException {
        if(!lawnMowerRegex.matcher(input).matches()) throw new InvalidInputException("unrecognized lawn mower: " + input);

        final String[] tokens = input.split(" ");

        final Position position = parsePosition(tokens[0] + " " + tokens[1]);
        final Orientation orientation = parseOrientation(tokens[2]);
        return new LawnMower(position, orientation);
    }


    @Override
    public MowingPlan parse(String filepath) throws InvalidInputException {
        try {
            final Iterator<String> linesIterator = Files.readAllLines(Path.of(filepath)).iterator();

            if(!linesIterator.hasNext()) {
                throw new InvalidInputException("file is empty");
            }

            final MowingPlan.MowingPlanBuilder mowingPlanBuilder = new MowingPlan.MowingPlanBuilder();

            final String lawnSizeLine = linesIterator.next();
            final Position topRightPosition = parsePosition(lawnSizeLine);
            mowingPlanBuilder
                    .setWidth(topRightPosition.x())
                    .setHeight(topRightPosition.y());

            while(linesIterator.hasNext()) {
                final String lawnMowerLine = linesIterator.next();
                if (!linesIterator.hasNext()) throw new InvalidInputException("missing actions for mower");
                final String motionsLine = linesIterator.next();

                final LawnMower lawnMower = parseLawnMower(lawnMowerLine);
                final List<Motion> motions = parseMotionSequence(motionsLine);

                mowingPlanBuilder.addLawnMowerWithMotions(lawnMower, motions);
            }

            return mowingPlanBuilder.build();
        } catch (IOException e) {
            throw new InvalidInputException("file not found");
        }
    }
}
