package com.cybemos.client.commands;

import com.cybemos.client.args.ReverseArgs;
import com.cybemos.services.ImageReader;
import com.cybemos.services.ImageService;
import com.cybemos.services.ImageWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;

public class ReverseCommand implements Command<ReverseArgs> {

    private static final Logger LOG = LoggerFactory.getLogger(ReverseCommand.class);

    private final ImageReader imageReader;
    private final ImageWriter imageWriter;
    private final ImageService imageService;

    public ReverseCommand() {
        imageReader = new ImageReader();
        imageWriter = new ImageWriter();
        imageService = new ImageService();
    }

    @Override
    public void execute(ReverseArgs args) {
        LOG.info("Reading {}...", args.getSource());
        BufferedImage source = imageReader.read(args.getSource());
        LOG.info("Reversing image with dimensions ({} x {})...", source.getWidth(), source.getHeight());
        BufferedImage blurredImage = imageService.reverse(source);
        LOG.info("Image with dimensions ({} x {}) reversed", source.getWidth(), source.getHeight());
        imageWriter.save(blurredImage, args.getDestination());
        LOG.info("Image saved as {}", args.getDestination());
    }
}
