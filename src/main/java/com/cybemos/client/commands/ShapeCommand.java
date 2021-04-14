package com.cybemos.client.commands;

import com.cybemos.client.args.ShapeArgs;
import com.cybemos.model.Color;
import com.cybemos.services.ImageReader;
import com.cybemos.services.ImageService;
import com.cybemos.services.ImageWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
import java.io.File;

public class ShapeCommand implements Command<ShapeArgs> {

    private static final Logger LOG = LoggerFactory.getLogger(BlurCommand.class);

    private final ImageReader imageReader;
    private final ImageWriter imageWriter;
    private final ImageService imageService;

    public ShapeCommand() {
        imageReader = new ImageReader();
        imageWriter = new ImageWriter();
        imageService = new ImageService();
    }

    @Override
    public void execute(ShapeArgs args) {
        LOG.info("Reading {}...", args.getSource());
        BufferedImage source = imageReader.read(new File(args.getSource()));
        LOG.info("Making shapes image with dimensions ({} x {})...", source.getWidth(), source.getHeight());
        BufferedImage blurredImage = args.isUnicolor()
                ? imageService.computeUniColorShape(source, args.getBlurLevel(),
                    new Color(255, 255, 255, 255), new Color(0, 0, 0, 255))
                : imageService.computeShape(source, args.getBlurLevel());
        LOG.info("Image with dimensions ({} x {}) shaped", source.getWidth(), source.getHeight());
        imageWriter.save(blurredImage, new File(args.getDestination()));
        LOG.info("Image saved as {}", args.getDestination());
    }
}
