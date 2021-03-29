package com.cybemos.model;

import java.util.Objects;

public final class Color {

    public static final Color TRANSPARENT = new Color(0, 0, 0, 0);

    private final int red;
    private final int green;
    private final int blue;
    private final int alpha;

    public Color(int red, int green, int blue, int alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public int getAlpha() {
        return alpha;
    }

    public int toARGB() {
        return (alpha << 24) + (red << 16) + (green << 8) + blue;
    }

    public static Color fromRGB(int rgb) {
        return new Color((rgb >> 16) & 0xff, (rgb >> 8) & 0xff, rgb & 0xff, (rgb >> 24) & 0xff);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Color color = (Color) o;
        return red == color.red &&
                green == color.green &&
                blue == color.blue &&
                alpha == color.alpha;
    }

    @Override
    public int hashCode() {
        return Objects.hash(red, green, blue, alpha);
    }

    @Override
    public String toString() {
        return "Color{" +
                "red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                ", alpha=" + alpha +
                '}';
    }

}
