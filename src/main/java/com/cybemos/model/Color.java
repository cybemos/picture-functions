package com.cybemos.model;

import lombok.Value;

@Value
public class Color {

    public static final Color TRANSPARENT = new Color(0, 0, 0, 0);

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
