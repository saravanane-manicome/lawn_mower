package io.mowitnow.lawn_mower.core.model;

import io.mowitnow.lawn_mower.core.motion.Motion;

import java.util.List;

public record LawnMowerProgram(LawnMower lawnMower, List<Motion> motions) {
}

