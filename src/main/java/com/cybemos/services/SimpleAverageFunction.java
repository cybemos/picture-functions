package com.cybemos.services;

import com.cybemos.model.Area;
import com.cybemos.model.Color;
import com.cybemos.model.ColorPosition;

import java.awt.image.BufferedImage;
import java.util.function.BiFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.function.Function.identity;

public class SimpleAverageFunction implements BiFunction<BufferedImage, Integer, Stream<ColorPosition>> {

    @Override
    public Stream<ColorPosition> apply(BufferedImage image, Integer blurLevel) {
        return IntStream.range(0, image.getHeight()).mapToObj(y ->
                IntStream.range(0, image.getWidth()).mapToObj(x -> {
                    Area area = getArea(image, x, y, blurLevel);
                    return new ColorPosition(x, y, computeAverage(image, area));
                })
        ).flatMap(identity());
    }

    private Area getArea(BufferedImage image, int x, int y, int blurLevel) {
        return new Area(
                Math.max(0, x - blurLevel),
                Math.max(0, y - blurLevel),
                Math.min(image.getWidth() - 1 - x, x + blurLevel),
                Math.min(image.getHeight() - 1 - y, y + blurLevel)
        );
    }

    private Color computeAverage(BufferedImage image, Area area) {
        return Color.fromRGB(image.getRGB(area.getX(), area.getY())); // TODO
        /*SumColor sumColor = new SumColor();
        for (int y = area.getY() ; y < area.getY() + area.getHeight() ; y++) {
            for (int x = area.getX() ; x < area.getX() + area.getWidth() ; x++) {
                Color color = Color.fromRGB(image.getRGB(x, y));
                sumColor.add(new SumColor(color));
            }
        }
        return sumColor.toColor();*/
    }

}
