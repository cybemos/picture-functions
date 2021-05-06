package com.cybemos.functions;

import com.cybemos.model.ColorPosition;

import java.awt.image.BufferedImage;
import java.util.stream.Stream;

@FunctionalInterface
public interface AverageFunction {

    /**
     * For each position in the picture, compute average color
     * @param image picture that you want the average colors
     * @param blurLevel Level of blur. The more the value is high, the more the image is blurred.
     * @return average color for each position
     */
    Stream<ColorPosition> average(BufferedImage image, int blurLevel);

}
