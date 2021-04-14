package com.cybemos.client;

import com.beust.jcommander.JCommander;
import com.cybemos.client.CommandType.CommandTypeVisitor;
import com.cybemos.client.args.*;
import com.cybemos.client.commands.*;

public class Client {

    public static void main(String[] args) {
        BlurArgs blurArgs = new BlurArgs();
        QuadTreeArgs quadTreeArgs = new QuadTreeArgs();
        ReverseArgs reverseArgs = new ReverseArgs();
        ShapeArgs shapeArgs = new ShapeArgs();
        DiffArgs diffArgs = new DiffArgs();
        JCommander jCommander = JCommander.newBuilder()
                .addCommand(blurArgs)
                .addCommand(quadTreeArgs)
                .addCommand(reverseArgs)
                .addCommand(shapeArgs)
                .addCommand(diffArgs)
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
                BlurCommand command = new BlurCommand();
                command.execute(blurArgs);
                return null;
            }

            @Override
            public Void visitQuadTree() {
                QuadTreeCommand command = new QuadTreeCommand();
                command.execute(quadTreeArgs);
                return null;
            }

            @Override
            public Void visitReverse() {
                ReverseCommand command = new ReverseCommand();
                command.execute(reverseArgs);
                return null;
            }

            @Override
            public Void visitShape() {
                ShapeCommand shapeCommand = new ShapeCommand();
                shapeCommand.execute(shapeArgs);
                return null;
            }

            @Override
            public Void visitDiff() {
                DiffCommand command = new DiffCommand();
                command.execute(diffArgs);
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
