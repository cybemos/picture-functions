package com.cybemos.functions;

import com.cybemos.model.Color;
import com.cybemos.model.ColorPosition;
import com.cybemos.model.SumColor;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class OldHorizontalAverageFunction implements AverageFunction {

    @Override
    public Stream<ColorPosition> average(BufferedImage image, int blurLevel) {
        int constant = blurLevel * 2 + 1;

        return IntStream.range(0, image.getHeight()).mapToObj(y -> {
            List<SumColor> sumColors = IntStream.range(0, constant)
                    .mapToObj(y2 -> SumColor.EMPTY)
                    .collect(toList());

            IntStream.range(0, Math.min(blurLevel + 1, image.getWidth()))
                    .forEach(x -> sumColors.set(x, sum(image, x, y, blurLevel)));

            return IntStream.range(0, image.getWidth()).mapToObj(x -> {
                if (x < blurLevel) {
                    return new ColorPosition(x, y, average(sumColors));
                }
                int index = (x + blurLevel) % constant;
                if (x + blurLevel < image.getWidth()) {
                    sumColors.set(index, sum(image, x + blurLevel, y, blurLevel));
                } else {
                    sumColors.set(index, SumColor.EMPTY);
                }
                return new ColorPosition(x, y, average(sumColors));
            });
        }).flatMap(Function.identity());
    }

    private Color average(List<SumColor> sumColors) {
        SumColor sumColor = sumColors.stream().reduce(SumColor::add).orElse(SumColor.EMPTY);
        return sumColor.toColor();
    }

    private SumColor sum(BufferedImage image, int x, int y, int blurLevel) {
        return IntStream.range(Math.max(0, y - blurLevel), Math.min(y + blurLevel, image.getHeight()))
                .mapToObj(y2 -> new SumColor(Color.fromRGB(image.getRGB(x, y2))))
                .reduce(SumColor::add).orElse(SumColor.EMPTY);
    }

}
