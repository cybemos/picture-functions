package com.cybemos.model;

import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * This class allow to add multiple colors to get the average color.
 *
 * @implSpec This class is immutable and thread-safe.
 */
@Value
@AllArgsConstructor(staticName = "of")
public class SumColor {

    public static final SumColor EMPTY = new SumColor();

    int number;
    int red;
    int green;
    int blue;
    int alpha;

    private SumColor() {
        number = 0;
        red = 0;
        green = 0;
        blue = 0;
        alpha = 0;
    }

    public static SumColor of(Color color) {
        return SumColor.of(1, color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    public SumColor add(SumColor sumColor) {
        if (EMPTY == this) {
            return sumColor;
        }
        if (sumColor == EMPTY) {
            return this;
        }
        return SumColor.of(
                number + sumColor.number,
                red + sumColor.red,
                green + sumColor.green,
                blue + sumColor.blue,
                alpha + sumColor.alpha
        );
    }

    /**
     * @return the average color or {@link Color#TRANSPARENT} if there is no color
     */
    public Color average() {
        if (number <= 0) {
            return Color.TRANSPARENT;
        }
        return new Color(red / number, green / number, blue / number, alpha / number);
    }

}
