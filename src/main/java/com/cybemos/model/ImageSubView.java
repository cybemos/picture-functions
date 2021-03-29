package com.cybemos.model;

import java.awt.image.BufferedImage;
import java.util.Objects;

public final class ImageSubView {

    private final BufferedImage image;
    private final Area area;

    public ImageSubView(BufferedImage image) {
        this(image, new Area(0, 0, image.getWidth(), image.getHeight()));
    }

    public ImageSubView(BufferedImage image, Area area) {
        this.image = Objects.requireNonNull(image);
        this.area = Objects.requireNonNull(area);
    }

    public BufferedImage getImage() {
        return image;
    }

    public Area getArea() {
        return area;
    }

}
