package io.mowitnow.lawn_mower.core.model;

public record Position(int x, int y) {
    public static Position of(int x, int y) {
        return new Position(x, y);
    }

    public Position withX(int x) {
        return new Position(x, y);
    }

    public Position withY(int y) {
        return new Position(x, y);
    }
}
