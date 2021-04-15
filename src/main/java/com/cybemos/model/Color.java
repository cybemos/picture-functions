package com.cybemos.model;

import lombok.Value;

@Value
public class Color {

    public static final Color TRANSPARENT = new Color(0, 0, 0, 0);
    public static final Color WHITE = new Color(255, 255, 255, 255);
    public static final Color BLACK = new Color(0, 0, 0, 255);
    public static final Color RED = new Color(255, 0, 0, 255);
    public static final Color GREEN = new Color(0, 255, 0, 255);
    public static final Color BLUE = new Color(0, 0, 255, 255);

    int red;
    int green;
    int blue;
    int alpha;

    public int toARGB() {
        return (alpha << 24) + (red << 16) + (green << 8) + blue;
    }

    public static Color fromRGB(int rgb) {
        return new Color((rgb >> 16) & 0xff, (rgb >> 8) & 0xff, rgb & 0xff, (rgb >> 24) & 0xff);
    }

}
