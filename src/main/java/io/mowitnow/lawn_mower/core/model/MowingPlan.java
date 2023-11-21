package io.mowitnow.lawn_mower.core.model;

import io.mowitnow.lawn_mower.core.motion.Motion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class MowingPlan {
    private final int width;
    private final int height;
    private final List<LawnMowerProgram> lawnMowerPrograms;

    MowingPlan(int width, int height, List<LawnMowerProgram> lawnMowerPrograms) {
        this.width = width;
        this.height = height;
        this.lawnMowerPrograms = lawnMowerPrograms;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public List<LawnMowerProgram> lawnMowerPrograms() {
        return lawnMowerPrograms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MowingPlan that = (MowingPlan) o;
        return width == that.width && height == that.height && Objects.equals(lawnMowerPrograms, that.lawnMowerPrograms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height, lawnMowerPrograms);
    }

    public static class MowingPlanBuilder {
        private int width;
        private int height;
        private final List<LawnMowerProgram> lawnMowerPrograms = new ArrayList<>();

        public MowingPlanBuilder setWidth(int width) {
            this.width = width;
            return this;
        }

        public MowingPlanBuilder setHeight(int height) {
            this.height = height;
            return this;
        }

        public MowingPlanBuilder addLawnMowerWithMotions(LawnMower lawnMower, List<Motion> motions) {
            lawnMowerPrograms.add(new LawnMowerProgram(lawnMower, motions));
            return this;
        }

        public MowingPlan build() {
            return new MowingPlan(width, height, Collections.unmodifiableList(lawnMowerPrograms));
        }
    }
}