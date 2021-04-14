package com.cybemos.client.commands;

import com.cybemos.client.args.QuadTreeArgs;
import com.cybemos.model.QuadTree;
import com.cybemos.services.ImageReader;
import com.cybemos.services.ImageWriter;
import com.cybemos.services.QuadTreeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
import java.io.File;

public class QuadTreeCommand implements Command<QuadTreeArgs> {

    private static final Logger LOG = LoggerFactory.getLogger(QuadTreeCommand.class);

    private final ImageReader imageReader;
    private final ImageWriter imageWriter;
    private final QuadTreeService quadTreeService;

    public QuadTreeCommand() {
        imageReader = new ImageReader();
        imageWriter = new ImageWriter();
        quadTreeService = new QuadTreeService();
    }

    @Override
    public void execute(QuadTreeArgs args) {
        LOG.info("Reading {}...", args.getSource());
        BufferedImage source = imageReader.read(new File(args.getSource()));
        LOG.info("Creating quadtree from image with dimensions ({} x {})...", source.getWidth(), source.getHeight());
        QuadTree quadTree = quadTreeService.createQuadTree(source, args.getDeepness());
        LOG.info("Quadtree created (deepness={}, number of nodes={})", quadTree.getDeepness(), quadTree.getNumberOfNodes());
        BufferedImage image = quadTreeService.toImage(quadTree);
        LOG.info("Quadtree mapped to Image with dimensions ({} x {})", source.getWidth(), source.getHeight());
        imageWriter.save(image, new File(args.getDestination()));
        LOG.info("Image saved as {}", args.getDestination());
    }

}
