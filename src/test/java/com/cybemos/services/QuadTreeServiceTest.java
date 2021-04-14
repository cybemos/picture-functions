package com.cybemos.services;

import com.cybemos.model.Area;
import com.cybemos.model.Color;
import com.cybemos.model.QuadTree;
import org.junit.Test;

import java.awt.image.BufferedImage;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class QuadTreeServiceTest {

    @Test(expected = IllegalArgumentException.class)
    public void when_max_deepness_is_0_then_throw_illegal_argument_exception() {
        // Given
        QuadTreeService quadTreeService = new QuadTreeService();
        BufferedImage source = new BufferedImage(1, 1, TYPE_INT_RGB);
        source.setRGB(0, 0, new Color(255, 0, 0, 255).toARGB());

        // When
        quadTreeService.createQuadTree(source, 0);

        // Then
        // throw IllegalArgumentException
        fail();
    }

    @Test
    public void when_image_has_dimension_200x100_then_quadtree_dimension_is_200x100() {
        // Given
        QuadTreeService quadTreeService = new QuadTreeService();
        BufferedImage source = new BufferedImage(200, 100, TYPE_INT_RGB);

        // When
        QuadTree quadTree = quadTreeService.createQuadTree(source, 10);

        // Then
        assertEquals(200, quadTree.getWidth());
        assertEquals(100, quadTree.getHeight());
    }

    @Test
    public void when_image_has_one_pixel_then_number_of_node_is_1() {
        // Given
        QuadTreeService quadTreeService = new QuadTreeService();
        BufferedImage source = new BufferedImage(1, 1, TYPE_INT_RGB);
        source.setRGB(0, 0, new Color(255, 0, 0, 255).toARGB());

        // When
        QuadTree quadTree = quadTreeService.createQuadTree(source, 10);

        // Then
        assertEquals(1, quadTree.getNumberOfNodes());
    }

    @Test
    public void when_image_has_one_pixel_then_deepness_is_1() {
        // Given
        QuadTreeService quadTreeService = new QuadTreeService();
        BufferedImage source = new BufferedImage(1, 1, TYPE_INT_RGB);
        source.setRGB(0, 0, new Color(255, 0, 0, 255).toARGB());

        // When
        QuadTree quadTree = quadTreeService.createQuadTree(source, 10);

        // Then
        assertEquals(1, quadTree.getDeepness());
    }

    @Test
    public void when_image_has_one_pixel_and_color_os_red_then_quadtree_color_should_be_red() {
        // Given
        QuadTreeService quadTreeService = new QuadTreeService();
        BufferedImage source = new BufferedImage(1, 1, TYPE_INT_RGB);
        Color red = new Color(255, 0, 0, 255);
        source.setRGB(0, 0, red.toARGB());

        // When
        QuadTree quadTree = quadTreeService.createQuadTree(source, 10);

        // Then
        assertEquals(red, quadTree.getRoot().getColor());
    }

    @Test
    public void when_image_has_2_pixels_and_those_pixels_doesnt_look_like_each_other_then_deepness_is_2() {
        // Given
        QuadTreeService quadTreeService = new QuadTreeService();
        BufferedImage source = new BufferedImage(2, 1, TYPE_INT_RGB);
        source.setRGB(0, 0, new Color(255, 0, 0, 255).toARGB());
        source.setRGB(1, 0, new Color(0, 255, 0, 255).toARGB());

        // When
        QuadTree quadTree = quadTreeService.createQuadTree(source, 10);

        // Then
        assertEquals(2, quadTree.getDeepness());
    }

    @Test
    public void when_image_has_2_pixels_and_those_pixels_look_like_each_other_then_deepness_is_1() {
        // Given
        QuadTreeService quadTreeService = new QuadTreeService();
        BufferedImage source = new BufferedImage(2, 1, TYPE_INT_RGB);
        source.setRGB(0, 0, new Color(255, 0, 0, 255).toARGB());
        source.setRGB(1, 0, new Color(250, 0, 0, 255).toARGB());

        // When
        QuadTree quadTree = quadTreeService.createQuadTree(source, 10);

        // Then
        assertEquals(1, quadTree.getDeepness());
    }

    @Test
    public void when_image_has_2_pixels_and_those_pixels_look_like_each_other_then_quadtree_color_should_be_the_average_color() {
        // Given
        QuadTreeService quadTreeService = new QuadTreeService();
        BufferedImage source = new BufferedImage(2, 1, TYPE_INT_RGB);
        source.setRGB(0, 0, new Color(255, 0, 0, 255).toARGB());
        source.setRGB(1, 0, new Color(249, 0, 0, 255).toARGB());

        // When
        QuadTree quadTree = quadTreeService.createQuadTree(source, 10);

        // Then
        assertEquals(new Color(252, 0, 0, 255), quadTree.getRoot().getColor());
    }

    @Test
    public void when_image_has_2_pixels_and_those_pixels_doesnt_look_like_each_other_and_max_deepness_is_1_then_deepness_is_1() {
        // Given
        QuadTreeService quadTreeService = new QuadTreeService();
        BufferedImage source = new BufferedImage(2, 1, TYPE_INT_RGB);
        source.setRGB(0, 0, new Color(255, 0, 0, 255).toARGB());
        source.setRGB(1, 0, new Color(0, 255, 0, 255).toARGB());

        // When
        QuadTree quadTree = quadTreeService.createQuadTree(source, 1);

        // Then
        assertEquals(1, quadTree.getDeepness());
    }

    @Test
    public void when_mapping_quadtree_with_dimensions_512x256_to_image_then_image_should_have_dimensions_512x256() {
        // Given
        QuadTreeService quadTreeService = new QuadTreeService();
        Color red = new Color(255, 0, 0, 255);
        QuadTree.Node root = QuadTree.Node.from(1, new Area(0, 0, 512, 256), red);
        QuadTree quadTree = new QuadTree(root);

        // When
        BufferedImage image = quadTreeService.toImage(quadTree);

        // Then
        assertEquals(512, image.getWidth());
        assertEquals(256, image.getHeight());
    }

    @Test
    public void when_mapping_quadtree_with_color_red_to_image_then_image_should_be_red() {
        // Given
        QuadTreeService quadTreeService = new QuadTreeService();
        Color red = new Color(255, 0, 0, 255);
        QuadTree.Node root = QuadTree.Node.from(1, new Area(0, 0, 1, 1), red);
        QuadTree quadTree = new QuadTree(root);

        // When
        BufferedImage image = quadTreeService.toImage(quadTree);

        // Then
        assertEquals(red.toARGB(), image.getRGB(0, 0));
    }

}