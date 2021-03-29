package com.cybemos.functions;

import com.cybemos.model.ColorPosition;

import java.awt.image.BufferedImage;
import java.util.stream.Stream;

@FunctionalInterface
public interface AverageFunction {

    Stream<ColorPosition> average(BufferedImage image, int blurLevel);

}
