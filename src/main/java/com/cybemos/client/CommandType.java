package com.cybemos.client;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public enum CommandType {

    BLUR("blur") {
        @Override
        public void visit(CommandTypeVisitor visitor) {
            visitor.visitBlur();
        }
    },
    QUADTREE("quadtree") {
        @Override
        public void visit(CommandTypeVisitor visitor) {
            visitor.visitQuadTree();
        }
    },
    REVERSE("reverse") {
        @Override
        public void visit(CommandTypeVisitor visitor) {
            visitor.visitReverse();
        }
    },
    SHAPE("shape") {
        @Override
        public void visit(CommandTypeVisitor visitor) {
            visitor.visitShape();
        }
    },
    DIFF("diff") {
        @Override
        public void visit(CommandTypeVisitor visitor) {
            visitor.visitDiff();
        }
    },
    HELP("help") {
        @Override
        public void visit(CommandTypeVisitor visitor) {
            visitor.visitHelp();
        }
    };

    private final String name;

    CommandType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void visit(CommandTypeVisitor visitor);

    public static Optional<CommandType> from(String str) {
        return Stream.of(CommandType.values())
                .filter(commandType -> Objects.equals(commandType.getName(), str))
                .findFirst();
    }

    public interface CommandTypeVisitor {
        void visitBlur();
        void visitQuadTree();
        void visitReverse();
        void visitShape();
        void visitDiff();
        void visitHelp();
    }

}
