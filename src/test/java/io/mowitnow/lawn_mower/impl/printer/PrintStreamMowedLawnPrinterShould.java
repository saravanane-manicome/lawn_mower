package io.mowitnow.lawn_mower.impl.printer;

import io.mowitnow.lawn_mower.core.formatter.MowedLawnFormatter;
import io.mowitnow.lawn_mower.core.model.MowedLawn;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.PrintStream;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PrintStreamMowedLawnPrinterShould {
    @Mock
    private PrintStream printStream;

    @Mock
    private MowedLawnFormatter mowedLawnFormatter;

    @InjectMocks
    private PrintStreamMowedLawnPrinter printStreamMowedLawnPrinter;

    @Test
    void formatAndPrint() {
        final MowedLawn mowedLawn = new MowedLawn(0, 0, Collections.emptyList());
        final List<String> lines = List.of("0 0 N", "1 2 E");
        when(mowedLawnFormatter.format(mowedLawn)).thenReturn(lines);

        printStreamMowedLawnPrinter.print(mowedLawn);

        final InOrder orderVerifier = inOrder(mowedLawnFormatter, printStream);
        orderVerifier.verify(mowedLawnFormatter).format(mowedLawn);
        for (String line: lines) {
            orderVerifier.verify(printStream).println(line);
        }
        orderVerifier.verifyNoMoreInteractions();
    }
}