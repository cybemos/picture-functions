package com.cybemos.services;

import com.cybemos.model.Color;
import org.junit.Test;

import static org.junit.Assert.*;

public class ColorServiceTest {

    @Test
    public void test_reverse() {
        // Given
        ColorService colorService = new ColorService();
        Color color = new Color(255, 0, 100, 255);

        // When
        Color output = colorService.reverse(color);

        // Then
        assertEquals(new Color(0, 255, 155, 255), output);
    }

    @Test
    public void test_gray() {
        // Given
        ColorService colorService = new ColorService();
        Color color = new Color(255, 0, 100, 255);

        // When
        Color output = colorService.gray(color);

        // Then
        // (255 + 100) / 3 = 118.33333333
        assertEquals(new Color(118, 118, 118, 255), output);
    }

    @Test
    public void truncate_values_when_using_gray() {
        // Given
        ColorService colorService = new ColorService();
        Color color = new Color(100, 14, 14, 255);

        // When
        Color output = colorService.gray(color);

        // Then
        // (100 + 14 + 14) / 3 = 42.66666666
        assertEquals(new Color(42, 42, 42, 255), output);
    }

    @Test
    public void test_diff() {
        // Given
        ColorService colorService = new ColorService();
        Color color1 = new Color(100, 50, 200, 255);
        Color color2 = new Color(48, 72, 200, 255);

        // When
        Color output = colorService.diff(color1, color2);

        // Then
        assertEquals(new Color(52, 22, 0, 255), output);
    }

}