package com.cybemos.client;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public enum CommandType {

    BLUR("blur") {
        @Override
        public <R> R visit(CommandTypeVisitor<R> visitor) {
            return visitor.visitBlur();
        }
    },
    QUADTREE("quadtree") {
        @Override
        public <R> R visit(CommandTypeVisitor<R> visitor) {
            return visitor.visitQuadTree();
        }
    },
    REVERSE("reverse") {
        @Override
        public <R> R visit(CommandTypeVisitor<R> visitor) {
            return visitor.visitReverse();
        }
    },
    SHAPE("shape") {
        @Override
        public <R> R visit(CommandTypeVisitor<R> visitor) {
            return visitor.visitShape();
        }
    },
    DIFF("diff") {
        @Override
        public <R> R visit(CommandTypeVisitor<R> visitor) {
            return visitor.visitDiff();
        }
    },
    HELP("help") {
        @Override
        public <R> R visit(CommandTypeVisitor<R> visitor) {
            return visitor.visitHelp();
        }
    };

    private final String name;

    CommandType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract <R> R visit(CommandTypeVisitor<R> visitor);

    public static Optional<CommandType> from(String str) {
        return Stream.of(CommandType.values())
                .filter(commandType -> Objects.equals(commandType.getName(), str))
                .findFirst();
    }

    public interface CommandTypeVisitor<R> {
        R visitBlur();
        R visitQuadTree();
        R visitReverse();
        R visitShape();
        R visitDiff();
        R visitHelp();
    }

}
