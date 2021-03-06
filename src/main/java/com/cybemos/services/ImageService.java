package com.cybemos.services;

import com.cybemos.functions.AverageFunction;
import com.cybemos.functions.HorizontalAverageFunction;
import com.cybemos.model.Color;

import java.awt.image.BufferedImage;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import static java.lang.Math.abs;
import static java.lang.Math.max;

public class ImageService {

    private final ColorService colorService;

    public ImageService() {
        this.colorService = new ColorService();
    }

    public BufferedImage blur(BufferedImage image, int blurLevel) {
        AverageFunction averageFunction = new HorizontalAverageFunction();
        return blur(image, averageFunction, blurLevel);
    }

    public BufferedImage blur(BufferedImage image, AverageFunction averageFunction, int blurLevel) {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(), image.getHeight(), TYPE_INT_ARGB);
        averageFunction.average(image, blurLevel).forEach(colorPosition -> {
            bufferedImage.setRGB(colorPosition.getX(), colorPosition.getY(), colorPosition.getColor().toARGB());
        });
        return bufferedImage;
    }

    public BufferedImage computeUniColorShape(BufferedImage image, int blurLevel, Color noShape, Color shapeColor) {
        AverageFunction averageFunction = new HorizontalAverageFunction();
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(), image.getHeight(), TYPE_INT_ARGB);
        averageFunction.average(image, blurLevel).forEach(colorPosition -> {
            Color color = Color.fromRGB(image.getRGB(colorPosition.getX(), colorPosition.getY()));
            Color averageColor = colorPosition.getColor();
            if (colorService.looksLike(averageColor, color, 0.05)) {
                bufferedImage.setRGB(colorPosition.getX(), colorPosition.getY(), noShape.toARGB());
            } else {
                bufferedImage.setRGB(colorPosition.getX(), colorPosition.getY(), shapeColor.toARGB());
            }
        });
        return bufferedImage;
    }

    public BufferedImage computeShape(BufferedImage image, int blurLevel) {
        AverageFunction averageFunction = new HorizontalAverageFunction();
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(), image.getHeight(), TYPE_INT_ARGB);
        averageFunction.average(image, blurLevel).forEach(colorPosition -> {
            Color color = Color.fromRGB(image.getRGB(colorPosition.getX(), colorPosition.getY()));
            Color averageColor = colorPosition.getColor();
            Color newColor = new Color(
                    max(255 - abs(color.getRed() - averageColor.getRed()) * 2, 0),
                    max(255 - abs(color.getGreen() - averageColor.getGreen()) * 2, 0),
                    max(255 - abs(color.getBlue() - averageColor.getBlue()) * 2, 0),
                    255
            );
            bufferedImage.setRGB(colorPosition.getX(), colorPosition.getY(), colorService.gray(newColor).toARGB());
        });
        return bufferedImage;
    }

    public BufferedImage diff(BufferedImage image1, BufferedImage image2) {
        if (image1.getWidth() != image2.getWidth() || image1.getHeight() != image2.getHeight()) {
            throw new IllegalArgumentException("images must have the same dimensions");
        }
        BufferedImage bufferedImage = new BufferedImage(image1.getWidth(), image1.getHeight(), TYPE_INT_ARGB);
        for (int x = 0 ; x < image1.getWidth() ; x++) {
            for (int y = 0 ; y < image1.getHeight() ; y++) {
                bufferedImage.setRGB(x, y, colorService.diff(
                        Color.fromRGB(image1.getRGB(x, y)),
                        Color.fromRGB(image2.getRGB(x, y))
                ).toARGB());
            }
        }
        return bufferedImage;
    }

    public BufferedImage reverse(BufferedImage image) {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(), image.getHeight(), TYPE_INT_ARGB);
        for (int x = 0 ; x < image.getWidth() ; x++) {
            for (int y = 0 ; y < image.getHeight() ; y++) {
                bufferedImage.setRGB(x, y, colorService.reverse(Color.fromRGB(image.getRGB(x, y))).toARGB());
            }
        }
        return bufferedImage;
    }

}
