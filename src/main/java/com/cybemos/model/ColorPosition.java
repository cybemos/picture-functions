package com.cybemos.model;

import java.util.Objects;

public final class ColorPosition {

    private final int x;
    private final int y;
    private final Color color;

    public ColorPosition(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColorPosition that = (ColorPosition) o;
        return x == that.x &&
                y == that.y &&
                Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, color);
    }

    @Override
    public String toString() {
        return "ColorPosition{" +
                "x=" + x +
                ", y=" + y +
                ", color=" + color +
                '}';
    }
}
