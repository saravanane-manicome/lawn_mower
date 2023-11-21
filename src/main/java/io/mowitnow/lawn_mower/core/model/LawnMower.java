package io.mowitnow.lawn_mower.core.model;

public record LawnMower(Position position, Orientation orientation) {
    public LawnMower withPosition(Position position) {
        return new LawnMower(position, orientation);
    }

    public LawnMower withOrientation(Orientation orientation) {
        return new LawnMower(position, orientation);
    }
}
