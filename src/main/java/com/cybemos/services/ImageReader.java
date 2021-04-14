package com.cybemos.services;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;

public class ImageReader {

    public BufferedImage read(File file) {
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public BufferedImage read(URL url) {
        try {
            return ImageIO.read(url);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
