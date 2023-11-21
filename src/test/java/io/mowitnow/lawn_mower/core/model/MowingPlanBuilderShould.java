package io.mowitnow.lawn_mower.core.model;

import io.mowitnow.lawn_mower.core.model.MowingPlan.MowingPlanBuilder;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MowingPlanBuilderShould {
    @Test
    void buildLawnWithoutSettingAnything() {
        final MowingPlan expectedMowingPlan = new MowingPlan(0, 0, Collections.emptyList());
        final MowingPlanBuilder mowingPlanBuilder = new MowingPlanBuilder();

        final MowingPlan mowingPlan = mowingPlanBuilder.build();

        assertEquals(expectedMowingPlan, mowingPlan);
    }

    @Test
    void buildLawnWithSetWidth() {
        final MowingPlan expectedMowingPlan = new MowingPlan(5, 0, Collections.emptyList());
        final MowingPlanBuilder mowingPlanBuilder = new MowingPlanBuilder();

        mowingPlanBuilder.setWidth(5);
        final MowingPlan mowingPlan = mowingPlanBuilder.build();

        assertEquals(expectedMowingPlan, mowingPlan);
    }

    @Test
    void buildLawnWithSetHeight() {
        final MowingPlan expectedMowingPlan = new MowingPlan(0, 5, Collections.emptyList());
        final MowingPlanBuilder mowingPlanBuilder = new MowingPlanBuilder();

        mowingPlanBuilder.setHeight(5);
        final MowingPlan mowingPlan = mowingPlanBuilder.build();

        assertEquals(expectedMowingPlan, mowingPlan);
    }

    @Test
    void buildLawnWithSetPrograms() {
        final LawnMower lawnMower = new LawnMower(Position.of(0, 0), Orientation.North);
        final List<LawnMowerProgram> programs = List.of(new LawnMowerProgram(lawnMower, Collections.emptyList()));
        final MowingPlan expectedMowingPlan = new MowingPlan(0, 0, programs);
        final MowingPlanBuilder mowingPlanBuilder = new MowingPlanBuilder();

        mowingPlanBuilder.addLawnMowerWithMotions(lawnMower, Collections.emptyList());
        final MowingPlan mowingPlan = mowingPlanBuilder.build();

        assertEquals(expectedMowingPlan, mowingPlan);
    }
}