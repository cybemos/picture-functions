package com.cybemos.services;

import com.cybemos.model.Color;
import com.cybemos.model.QuadTree;
import org.junit.Ignore;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.File;

public class QuadTreeServiceTest {

    @Test
    @Ignore
    public void test_quadtree() {
        ImageReader imageReader = new ImageReader();
        ImageWriter imageWriter = new ImageWriter();
        BufferedImage image = imageReader.read(new File("C:\\Users\\nicol\\Pictures\\antoine.png"));

        QuadTreeService service = new QuadTreeService();
        QuadTree tree = service.createQuadTree(image, 11);
        System.out.println(tree);
        BufferedImage image2 = service.toImage(tree);
        imageWriter.save(image2, new File("C:\\Users\\nicol\\Pictures\\antoine2.png"));
    }

    @Test
    @Ignore
    public void test_blur() {
        ImageReader imageReader = new ImageReader();
        ImageWriter imageWriter = new ImageWriter();
        BufferedImage image = imageReader.read(new File("C:\\Users\\nicol\\Pictures\\antoine.png"));

        ImageService imageService = new ImageService();
        BufferedImage blurredImage = imageService.blur(image, 15);
        imageWriter.save(blurredImage, new File("C:\\Users\\nicol\\Pictures\\antoine_blur.png"));
    }

    @Test
    //@Ignore
    public void test_shapes() {
        ImageReader imageReader = new ImageReader();
        ImageWriter imageWriter = new ImageWriter();
        BufferedImage image = imageReader.read(new File("C:\\Users\\Cybemos\\Pictures\\Minorque\\20170902_112142.jpg"));

        ImageService imageService = new ImageService();
        Color shape = new Color(180, 180, 180, 255);
        BufferedImage blurredImage = imageService.computeShape(image, 7, shape);
        imageWriter.save(blurredImage, new File("C:\\Users\\Cybemos\\Pictures\\shape.png"));
    }

    @Test
    //@Ignore
    public void test_shapes_2() {
        ImageReader imageReader = new ImageReader();
        ImageWriter imageWriter = new ImageWriter();
        BufferedImage image = imageReader.read(new File("C:\\Users\\Cybemos\\Pictures\\Minorque\\20170902_112142.jpg"));

        ImageService imageService = new ImageService();
        BufferedImage blurredImage = imageService.computeShape2(image, 7);
        imageWriter.save(blurredImage, new File("C:\\Users\\Cybemos\\Pictures\\shape_2.png"));
    }

    @Test
    @Ignore
    public void test_diff() {
        ImageReader imageReader = new ImageReader();
        ImageWriter imageWriter = new ImageWriter();
        BufferedImage image = imageReader.read(new File("C:\\Users\\nicol\\Pictures\\antoine.png"));
        BufferedImage modifiedImage = imageReader.read(new File("C:\\Users\\nicol\\Pictures\\antoine2.png"));

        ImageService imageService = new ImageService();
        BufferedImage blurredImage = imageService.diff(image, modifiedImage);
        imageWriter.save(blurredImage, new File("C:\\Users\\nicol\\Pictures\\antoine_diff.png"));
    }

    @Test
    @Ignore
    public void test_reverse() {
        ImageReader imageReader = new ImageReader();
        ImageWriter imageWriter = new ImageWriter();
        BufferedImage image = imageReader.read(new File("C:\\Users\\nicol\\Pictures\\antoine.png"));

        ImageService imageService = new ImageService();
        BufferedImage blurredImage = imageService.reverse(image);
        imageWriter.save(blurredImage, new File("C:\\Users\\nicol\\Pictures\\antoine_inverse.png"));
    }

    @Test
    //@Ignore
    public void compare_average() {
        ImageReader imageReader = new ImageReader();
        ImageWriter imageWriter = new ImageWriter();
        BufferedImage image = imageReader.read(new File("C:\\Users\\Cybemos\\Pictures\\Minorque\\20170902_112142.jpg"));

        ImageService imageService = new ImageService();

        int blurLevel = 7;

        long t1 = System.currentTimeMillis();
        BufferedImage blurredImage = imageService.blur(image, blurLevel);
        long t2 = System.currentTimeMillis();
        System.out.println(String.format("Time taken (normal) : %s ms", t2 - t1));
        imageWriter.save(blurredImage, new File("C:\\Users\\Cybemos\\Pictures\\blur.jpg"));

        /*long t3 = System.currentTimeMillis();
        BufferedImage blurredImage2 = imageService.simpleBlur(image, blurLevel);
        long t4 = System.currentTimeMillis();
        System.out.println(String.format("Time taken (simple) : %s ms", t4 - t3));
        imageWriter.save(blurredImage2, new File("C:\\Users\\Cybemos\\Pictures\\simple_blur.jpg"));*/
    }

}