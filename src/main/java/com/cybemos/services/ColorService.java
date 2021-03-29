package com.cybemos.services;

import com.cybemos.model.Color;

public class ColorService {

    public boolean looksLike(Color c1, Color c2, double quality) {
        double maxDiff = quality * 255;
        return Math.abs(c1.getRed() - c2.getRed()) <= maxDiff
                && Math.abs(c1.getGreen() - c2.getGreen()) <= maxDiff
                && Math.abs(c1.getBlue() - c2.getBlue()) <= maxDiff;
    }

    public Color diff(Color c1, Color c2) {
        return new Color(
                Math.abs(c1.getRed() - c2.getRed()),
                Math.abs(c1.getGreen() - c2.getGreen()),
                Math.abs(c1.getBlue() - c2.getBlue()),
                255
        );
    }

    public Color reverse(Color color) {
        return new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue(), color.getAlpha());
    }

    public Color gray(Color color) {
        int sum = color.getRed() + color.getGreen() + color.getBlue();
        int greyValue = sum / 3;
        return new Color(greyValue, greyValue, greyValue, 255);
    }

}
