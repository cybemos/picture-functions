package com.cybemos.model;

import lombok.NonNull;
import lombok.ToString;
import lombok.Value;

import java.util.List;
import java.util.Objects;

/**
 * @implSpec This class is immutable and thread-safe.
 */
@Value
public class QuadTree {

    int deepness;
    int numberOfNodes;
    @ToString.Exclude
    @NonNull Node root;

    public QuadTree(Node root) {
        this.root = Objects.requireNonNull(root);
        if (root.getDeepness() != 1) {
            throw new IllegalArgumentException("Root deepness must be 1");
        }
        this.deepness = root.computeDeepness();
        this.numberOfNodes = root.computeNumberOfNodes();
    }

    public int getWidth() {
        return root.getArea().getWidth();
    }

    public int getHeight() {
        return root.getArea().getHeight();
    }

    /**
     * @implSpec This class is immutable and thread-safe.
     */
    @Value
    public static class Node {

        int deepness;
        @NonNull List<Node> children;
        @NonNull Area area;
        Color color;

        private Node(int deepness, Area area, Color color, List<Node> children) {
            this.deepness = deepness;
            this.area = area;
            this.color = color;
            this.children = List.copyOf(children);
        }

        public static Node from(int deepness, Area area, Color color) {
            return new Node(deepness, area, color, List.of());
        }

        public static Node from(int deepness, Area area, List<Node> children) {
            return new Node(deepness, area, null, children);
        }

        public int computeDeepness() {
            return children.stream().mapToInt(Node::computeDeepness).max().orElse(deepness);
        }

        public int computeNumberOfNodes() {
            return children.stream().mapToInt(Node::computeNumberOfNodes).sum() + 1;
        }

    }

}
