package com.cybemos.services;

import com.cybemos.model.*;

import java.awt.image.BufferedImage;
import java.util.List;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import static java.util.stream.Collectors.toList;

public class QuadTreeService {

    private final AreaSplitter areaSplitter;
    private final ColorService colorService;

    public QuadTreeService() {
        this.areaSplitter = new AreaSplitter();
        this.colorService = new ColorService();
    }

    public QuadTree createQuadTree(BufferedImage image, int maxDeepness) {
        if (maxDeepness < 1) {
            throw new IllegalArgumentException("maxDeepness < 1");
        }
        ImageSubView view = ImageSubView.from(image);
        QuadTree.Node root = createNode(maxDeepness, 1, view);
        return new QuadTree(root);
    }

    public BufferedImage toImage(QuadTree quadTree) {
        BufferedImage image = new BufferedImage(quadTree.getWidth(), quadTree.getHeight(), TYPE_INT_ARGB);
        QuadTree.Node root = quadTree.getRoot();
        writeToImage(image, root);
        return image;
    }

    private void writeToImage(BufferedImage image, QuadTree.Node node) {
        if (node.getColor() != null) {
            Area area = node.getArea();
            int color = node.getColor().toARGB();
            for (int i = area.getX() ; i < area.getX() + area.getWidth() ; i++) {
                for (int j = area.getY() ; j < area.getY() + area.getHeight() ; j++) {
                    image.setRGB(i, j, color);
                }
            }
        }
        node.getChildren().forEach(child -> writeToImage(image, child));
    }

    private QuadTree.Node createNode(int maxDeepness, int deepness, ImageSubView view) {
        Color averageColor = computeAverage(view);
        if (deepness >= maxDeepness) {
            return QuadTree.Node.from(deepness, view.getArea(), averageColor);
        }
        if (isAlmostHomogeneous(view, averageColor)) {
            return QuadTree.Node.from(deepness, view.getArea(), averageColor);
        }

        List<Area> areas = areaSplitter.split(view.getArea());
        if (areas.size() <= 1) {
            return QuadTree.Node.from(deepness, view.getArea(), averageColor);
        }
        List<QuadTree.Node> nodes = areas.stream()
                .map(area -> ImageSubView.from(view.getImage(), area))
                .map(subView -> createNode(maxDeepness, deepness + 1, subView))
                .collect(toList());
        return QuadTree.Node.from(deepness, view.getArea(), nodes);
    }

    private boolean isAlmostHomogeneous(ImageSubView view, Color averageColor) {
        Area area = view.getArea();
        for (int i = area.getX() ; i < area.getX() + area.getWidth() ; i++) {
            for (int j = area.getY() ; j < area.getY() + area.getHeight() ; j++) {
                Color color = Color.fromRGB(view.getImage().getRGB(i, j));
                if (!colorService.looksLike(averageColor, color, 0.1)) {
                    return false;
                }
            }
        }
        return true;
    }

    private Color computeAverage(ImageSubView view) {
        SumColor sumColor = SumColor.EMPTY;
        Area area = view.getArea();
        for (int i = area.getX() ; i < area.getX() + area.getWidth() ; i++) {
            for (int j = area.getY() ; j < area.getY() + area.getHeight() ; j++) {
                Color color = Color.fromRGB(view.getImage().getRGB(i, j));
                sumColor = sumColor.add(SumColor.of(color));
            }
        }
        return sumColor.average();
    }

}
