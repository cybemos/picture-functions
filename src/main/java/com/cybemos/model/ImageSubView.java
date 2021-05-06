package com.cybemos.model;

import lombok.NonNull;
import lombok.Value;

import java.awt.image.BufferedImage;

@Value(staticConstructor = "from")
public class ImageSubView {

    @NonNull BufferedImage image;
    @NonNull Area area;

    public static ImageSubView from(BufferedImage image) {
        return new ImageSubView(image, Area.of(0, 0, image.getWidth(), image.getHeight()));
    }

}
