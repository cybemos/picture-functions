package com.cybemos.services;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;

public class ImageReader {

    public BufferedImage read(File file) {
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
