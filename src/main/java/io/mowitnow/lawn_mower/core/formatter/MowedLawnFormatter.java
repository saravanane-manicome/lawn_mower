package io.mowitnow.lawn_mower.core.formatter;

import io.mowitnow.lawn_mower.core.model.MowedLawn;

import java.util.List;

public interface MowedLawnFormatter {
    List<String> format(MowedLawn mowedLawn);
}
