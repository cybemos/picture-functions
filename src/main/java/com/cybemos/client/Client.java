package com.cybemos.client;

import com.beust.jcommander.JCommander;
import com.cybemos.client.CommandType.CommandTypeVisitor;
import com.cybemos.services.ImageReader;
import com.cybemos.services.ImageService;
import com.cybemos.services.ImageWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
import java.io.File;

public class Client {

    private static final Logger LOG = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) {
        BlurArgs blurArgs = new BlurArgs();
        JCommander jCommander = JCommander.newBuilder()
                .addCommand(blurArgs)
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
                BufferedImage blurredImage = imageService.blur(source, 7);
                LOG.info("Image with dimensions ({} x {}) blurred", source.getWidth(), source.getHeight());
                imageWriter.save(blurredImage, new File(blurArgs.getDestination()));
                LOG.info("Image saved as {}", blurArgs.getDestination());
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
