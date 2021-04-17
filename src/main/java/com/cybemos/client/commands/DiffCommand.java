package com.cybemos.client.commands;

import com.cybemos.client.args.DiffArgs;
import com.cybemos.services.ImageReader;
import com.cybemos.services.ImageService;
import com.cybemos.services.ImageWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;

public class DiffCommand implements Command<DiffArgs> {

    private static final Logger LOG = LoggerFactory.getLogger(DiffCommand.class);

    private final ImageReader imageReader;
    private final ImageWriter imageWriter;
    private final ImageService imageService;

    public DiffCommand() {
        imageReader = new ImageReader();
        imageWriter = new ImageWriter();
        imageService = new ImageService();
    }

    @Override
    public void execute(DiffArgs args) {
        if (args.getSources().size() != 2) {
            throw new IllegalArgumentException("You must specify 2 sources");
        }
        LOG.info("Reading {}...", args.getSources().get(0));
        BufferedImage source1 = imageReader.read(args.getSources().get(0));
        LOG.info("Reading {}...", args.getSources().get(1));
        BufferedImage source2 = imageReader.read(args.getSources().get(1));
        LOG.info("Making diff image with dimensions ({} x {})...", source1.getWidth(), source1.getHeight());
        BufferedImage diff = imageService.diff(source1, source2);
        LOG.info("Image with dimensions ({} x {}) shaped", source1.getWidth(), source1.getHeight());
        imageWriter.save(diff, args.getDestination());
        LOG.info("Image saved as {}", args.getDestination());
    }
}
