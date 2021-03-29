package com.cybemos.model;

import java.util.List;
import java.util.Objects;

public final class QuadTree {

    private final int deepness;
    private final int numberOfNodes;
    private final Node root;

    public QuadTree(Node root) {
        this.root = Objects.requireNonNull(root);
        if (root.getDeepness() != 1) {
            throw new IllegalArgumentException("Root deepness must be 1");
        }
        this.deepness = root.computeDeepness();
        this.numberOfNodes = root.computeNumberOfNodes();
    }

    public int getDeepness() {
        return deepness;
    }

    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    public int getWidth() {
        return root.getArea().getWidth();
    }

    public int getHeight() {
        return root.getArea().getHeight();
    }

    public Node getRoot() {
        return root;
    }

    @Override
    public String toString() {
        return "QuadTree{" +
                "deepness=" + deepness +
                ", numberOfNodes=" + numberOfNodes +
                '}';
    }

    public static class Node {

        private final int deepness;
        private final List<Node> children;
        private final Area area;
        private final Color color;

        private Node(int deepness, Area area, Color color, List<Node> children) {
            this.deepness = deepness;
            this.area = area;
            this.color = color;
            this.children = children;
        }

        public static Node from(int deepness, Area area, Color color) {
            return new Node(deepness, area, color, List.of());
        }

        public static Node from(int deepness, Area area, List<Node> children) {
            return new Node(deepness, area, null, children);
        }

        public int getDeepness() {
            return deepness;
        }

        public List<Node> getChildren() {
            return children;
        }

        public Area getArea() {
            return area;
        }

        public Color getColor() {
            return color;
        }

        public int computeDeepness() {
            return children.stream().mapToInt(Node::computeDeepness).max().orElse(deepness);
        }

        public int computeNumberOfNodes() {
            return children.stream().mapToInt(Node::computeNumberOfNodes).sum() + 1;
        }

    }

}
