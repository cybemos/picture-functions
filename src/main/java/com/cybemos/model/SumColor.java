package com.cybemos.model;

public final class SumColor {

    public static final SumColor EMPTY = new SumColor();

    private final int number;
    private final int red;
    private final int green;
    private final int blue;
    private final int alpha;

    private SumColor() {
        number = 0;
        red = 0;
        green = 0;
        blue = 0;
        alpha = 0;
    }

    private SumColor(int number, int red, int green, int blue, int alpha) {
        this.number = number;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public SumColor(Color color) {
        number = 1;
        red = color.getRed();
        green = color.getGreen();
        blue = color.getBlue();
        alpha = color.getAlpha();
    }

    public SumColor add(SumColor sumColor) {
        if (EMPTY == this) {
            return sumColor;
        }
        if (sumColor == EMPTY) {
            return this;
        }
        return new SumColor(
                number + sumColor.number,
                red + sumColor.red,
                green + sumColor.green,
                blue + sumColor.blue,
                alpha + sumColor.alpha
        );
    }

    public Color toColor() {
        if (number <= 0) {
            return Color.TRANSPARENT;
        }
        return new Color(red / number, green / number, blue / number, alpha / number);
    }

}
