package com.cybemos.services;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;

public class ImageWriter {

    public void save(BufferedImage image, File outputFile) {
        try {
            ImageIO.write(image, "png", outputFile);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
