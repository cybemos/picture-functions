package com.cybemos.client.commands;

import com.cybemos.client.args.BlurArgs;
import com.cybemos.services.ImageReader;
import com.cybemos.services.ImageService;
import com.cybemos.services.ImageWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;

public class BlurCommand implements Command<BlurArgs> {

    private static final Logger LOG = LoggerFactory.getLogger(BlurCommand.class);

    private final ImageReader imageReader;
    private final ImageWriter imageWriter;
    private final ImageService imageService;

    public BlurCommand() {
        imageReader = new ImageReader();
        imageWriter = new ImageWriter();
        imageService = new ImageService();
    }

    @Override
    public void execute(BlurArgs args) {
        LOG.info("Reading {}...", args.getSource());
        BufferedImage source = imageReader.read(args.getSource());
        LOG.info("Blurring image with dimensions ({} x {})...", source.getWidth(), source.getHeight());
        BufferedImage blurredImage = imageService.blur(source, args.getBlurLevel());
        LOG.info("Image with dimensions ({} x {}) blurred", source.getWidth(), source.getHeight());
        imageWriter.save(blurredImage, args.getDestination());
        LOG.info("Image saved as {}", args.getDestination());
    }

}
