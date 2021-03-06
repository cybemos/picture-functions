package com.cybemos.client;

import com.beust.jcommander.JCommander;
import com.cybemos.client.CommandType.CommandTypeVisitor;
import com.cybemos.client.args.*;
import com.cybemos.client.commands.*;

public class Client {

    private final JCommander jCommander;
    private final CommandTypeVisitor commandTypeExecutorVisitor;

    public Client() {
        BlurArgs blurArgs = new BlurArgs();
        QuadTreeArgs quadTreeArgs = new QuadTreeArgs();
        ReverseArgs reverseArgs = new ReverseArgs();
        ShapeArgs shapeArgs = new ShapeArgs();
        DiffArgs diffArgs = new DiffArgs();
        this.jCommander = JCommander.newBuilder()
                .addCommand(CommandType.BLUR.getName(), blurArgs)
                .addCommand(CommandType.QUADTREE.getName(), quadTreeArgs)
                .addCommand(CommandType.REVERSE.getName(), reverseArgs)
                .addCommand(CommandType.SHAPE.getName(), shapeArgs)
                .addCommand(CommandType.DIFF.getName(), diffArgs)
                .build();
        this.commandTypeExecutorVisitor = new CommandTypeVisitor() {
            @Override
            public void visitBlur() {
                BlurCommand command = new BlurCommand();
                command.execute(blurArgs);
            }

            @Override
            public void visitQuadTree() {
                QuadTreeCommand command = new QuadTreeCommand();
                command.execute(quadTreeArgs);
            }

            @Override
            public void visitReverse() {
                ReverseCommand command = new ReverseCommand();
                command.execute(reverseArgs);
            }

            @Override
            public void visitShape() {
                ShapeCommand shapeCommand = new ShapeCommand();
                shapeCommand.execute(shapeArgs);
            }

            @Override
            public void visitDiff() {
                DiffCommand command = new DiffCommand();
                command.execute(diffArgs);
            }

            @Override
            public void visitHelp() {
                jCommander.usage();
            }
        };
    }

    public void execute(String[] args) {
        jCommander.parse(args);
        String parsedCommand = jCommander.getParsedCommand();
        if (parsedCommand == null) {
            jCommander.usage();
            return;
        }
        CommandType commandType = CommandType.from(parsedCommand)
                .orElseThrow(() -> new IllegalArgumentException("Unknown command : " + parsedCommand));
        commandType.visit(commandTypeExecutorVisitor);
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.execute(args);
    }

}
