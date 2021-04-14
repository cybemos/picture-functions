package com.cybemos.client;

import com.beust.jcommander.JCommander;
import com.cybemos.client.CommandType.CommandTypeVisitor;
import com.cybemos.model.QuadTree;
import com.cybemos.services.ImageReader;
import com.cybemos.services.ImageService;
import com.cybemos.services.ImageWriter;
import com.cybemos.services.QuadTreeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
import java.io.File;

public class Client {

    private static final Logger LOG = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) {
        BlurArgs blurArgs = new BlurArgs();
        QuadTreeArgs quadTreeArgs = new QuadTreeArgs();
        JCommander jCommander = JCommander.newBuilder()
                .addCommand(blurArgs)
                .addCommand(quadTreeArgs)
                .build();
        jCommander.parse(args);

        String parsedCommand = jCommander.getParsedCommand();
        if (parsedCommand == null) {
            help();
            return;
        }

        CommandType commandType = CommandType.from(parsedCommand)
                .orElseThrow(() -> new RuntimeException("Unknown command : " + parsedCommand));

        commandType.visit(new CommandTypeVisitor<Void>() {
            @Override
            public Void visitBlur() {
                ImageReader imageReader = new ImageReader();
                ImageWriter imageWriter = new ImageWriter();
                ImageService imageService = new ImageService();
                LOG.info("Reading {}...", blurArgs.getSource());
                BufferedImage source = imageReader.read(new File(blurArgs.getSource()));
                LOG.info("Blurring image with dimensions ({} x {})...", source.getWidth(), source.getHeight());
                BufferedImage blurredImage = imageService.blur(source, blurArgs.getBlurLevel());
                LOG.info("Image with dimensions ({} x {}) blurred", source.getWidth(), source.getHeight());
                imageWriter.save(blurredImage, new File(blurArgs.getDestination()));
                LOG.info("Image saved as {}", blurArgs.getDestination());
                return null;
            }

            @Override
            public Void visitQuadTree() {
                ImageReader imageReader = new ImageReader();
                ImageWriter imageWriter = new ImageWriter();
                QuadTreeService quadTreeService = new QuadTreeService();
                LOG.info("Reading {}...", quadTreeArgs.getSource());
                BufferedImage source = imageReader.read(new File(quadTreeArgs.getSource()));
                LOG.info("Creating quadtree from image with dimensions ({} x {})...", source.getWidth(), source.getHeight());
                QuadTree quadTree = quadTreeService.createQuadTree(source, quadTreeArgs.getDeepness());
                LOG.info("Quadtree created (deepness={}, number of nodes={})", quadTree.getDeepness(), quadTree.getNumberOfNodes());
                BufferedImage image = quadTreeService.toImage(quadTree);
                LOG.info("Quadtree mapped to Image with dimensions ({} x {})", source.getWidth(), source.getHeight());
                imageWriter.save(image, new File(quadTreeArgs.getDestination()));
                LOG.info("Image saved as {}", quadTreeArgs.getDestination());
                return null;
            }

            @Override
            public Void visitHelp() {
                help();
                return null;
            }
        });
    }

    private static void help() {
        System.out.println("Available commands :");
        for (CommandType commandType : CommandType.values()) {
            System.out.println(" - " + commandType.getName());
        }
    }

}
