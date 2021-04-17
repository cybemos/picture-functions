package com.cybemos.services;

import com.cybemos.functions.HorizontalAverageFunction;
import com.cybemos.functions.SimpleAverageFunction;
import com.cybemos.model.Color;
import com.cybemos.model.ColorPosition;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.util.List;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

public class AverageFunctionTest {

    @Test
    public void when_picture_has_one_pixel_then_average_is_same_as_input() {
        // Given
        BufferedImage source = new BufferedImage(1, 1, TYPE_INT_ARGB);
        Color color = new Color(196, 56, 42, 255);
        source.setRGB(0, 0, color.toARGB());
        HorizontalAverageFunction averageFunction = new HorizontalAverageFunction();

        // When
        List<ColorPosition> colorPositions = averageFunction.average(source, 3).collect(toList());

        // Then
        assertEquals(1, colorPositions.size());
        ColorPosition colorPosition = colorPositions.get(0);
        assertEquals(0, colorPosition.getX());
        assertEquals(0, colorPosition.getY());
        assertEquals(color, colorPosition.getColor());
    }

    @Test
    public void when_picture_has_two_pixels_then_output_has_one_color_that_is_the_average() {
        // Given
        BufferedImage source = new BufferedImage(2, 1, TYPE_INT_ARGB);
        Color color1 = new Color(197, 56, 42, 255);
        Color color2 = new Color(25, 48, 74, 255);

        Color average = new Color(
                (color1.getRed() + color2.getRed()) / 2,
                (color1.getGreen() + color2.getGreen()) / 2,
                (color1.getBlue() + color2.getBlue()) / 2,
                (color1.getAlpha() + color2.getAlpha()) / 2
        );

        source.setRGB(0, 0, color1.toARGB());
        source.setRGB(1, 0, color2.toARGB());
        HorizontalAverageFunction averageFunction = new HorizontalAverageFunction();

        // When
        List<ColorPosition> colorPositions = averageFunction.average(source, 3)
                .sorted(comparingInt(ColorPosition::getX).thenComparingInt(ColorPosition::getY))
                .collect(toList());

        // Then
        assertEquals(2, colorPositions.size());

        ColorPosition expectedColorPosition1 = new ColorPosition(0, 0, average);
        assertEquals(expectedColorPosition1, colorPositions.get(0));

        ColorPosition expectedColorPosition2 = new ColorPosition(1, 0, average);
        assertEquals(expectedColorPosition2, colorPositions.get(1));
    }

    @Test
    public void compare_average_from_multiple_implementations() {
        // Given
        BufferedImage source = new BufferedImage(10, 1, TYPE_INT_ARGB);
        List<Color> colors = List.of(
                new Color(197, 56, 42, 255),
                new Color(25, 48, 74, 255),
                new Color(50, 75, 65, 255),
                new Color(41, 48, 36, 255),
                new Color(32, 65, 241, 255),
                new Color(74, 14, 32, 255),
                new Color(32, 85, 147, 255),
                new Color(48, 68, 74, 255),
                new Color(145, 85, 74, 255),
                new Color(152, 48, 32, 255)
        );
        for (int x = 0 ; x < colors.size() ; x++) {
            source.setRGB(x, 0, colors.get(x).toARGB());
        }

        int blurLevel = 1;

        HorizontalAverageFunction horizontalAverageFunction = new HorizontalAverageFunction();
        SimpleAverageFunction simpleAverageFunction = new SimpleAverageFunction();

        // When
        List<ColorPosition> colorPositions = horizontalAverageFunction.average(source, blurLevel)
                .sorted(comparingInt(ColorPosition::getX).thenComparingInt(ColorPosition::getY))
                .collect(toList());

        // Then
        List<ColorPosition> expectedValues = simpleAverageFunction.average(source, blurLevel)
                .sorted(comparingInt(ColorPosition::getX).thenComparingInt(ColorPosition::getY))
                .collect(toList());

        assertEquals(expectedValues.size(), colorPositions.size());
        for (int i = 0 ; i < colorPositions.size() ; i++) {
            assertEquals("unexpected error at index " + i, expectedValues.get(i), colorPositions.get(i));
        }
    }

}