package io.mowitnow.lawn_mower;

import io.mowitnow.lawn_mower.core.exception.InvalidInputException;
import io.mowitnow.lawn_mower.core.model.MowedLawn;
import io.mowitnow.lawn_mower.core.model.MowingPlan;
import io.mowitnow.lawn_mower.core.parser.MowingPlanParser;
import io.mowitnow.lawn_mower.core.printer.MowedLawnPrinter;
import io.mowitnow.lawn_mower.core.runner.MowingPlanRunner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LawnMowingApplicationShould {
    @Mock
    private MowingPlanParser mowingPlanParser;

    @Mock
    private MowingPlanRunner mowingPlanRunner;

    @Mock
    private MowedLawnPrinter mowedLawnPrinter;

    @InjectMocks
    private LawnMowingApplication lawnMowingApplication;


    @Test
    void runInCorrectOrder() throws InvalidInputException {
        final String filePath = "test";
        final MowingPlan mowingPlan = new MowingPlan.MowingPlanBuilder().build();
        final MowedLawn mowedLawn = new MowedLawn(0, 0, Collections.emptyList());
        when(mowingPlanParser.parse(filePath)).thenReturn(mowingPlan);
        when(mowingPlanRunner.run(mowingPlan)).thenReturn(mowedLawn);

        lawnMowingApplication.process(filePath);

        final InOrder orderVerifier = inOrder(mowingPlanParser, mowingPlanRunner, mowedLawnPrinter);
        orderVerifier.verify(mowingPlanParser).parse(filePath);
        orderVerifier.verify(mowingPlanRunner).run(mowingPlan);
        orderVerifier.verify(mowedLawnPrinter).print(mowedLawn);
        orderVerifier.verifyNoMoreInteractions();
    }

}