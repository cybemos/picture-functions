package com.cybemos.services;

import com.cybemos.model.Area;
import org.junit.Test;

import java.util.List;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

public class AreaSplitterTest {

    private AreaSplitter splitter = new AreaSplitter();

    @Test
    public void test_normal_split() {
        // Given
        Area area = new Area(0, 0, 2, 2);

        // When
        List<Area> subAreas = splitter.split(area)
                .stream()
                .sorted(comparingInt(Area::getX).thenComparing(Area::getY))
                .collect(toList());

        // Then
        assertEquals(4, subAreas.size());
        assertEquals(new Area(0, 0, 1, 1), subAreas.get(0));
        assertEquals(new Area(0, 1, 1, 1), subAreas.get(1));
        assertEquals(new Area(1, 0, 1, 1), subAreas.get(2));
        assertEquals(new Area(1, 1, 1, 1), subAreas.get(3));
    }

    @Test
    public void test_unable_to_split() {
        // Given
        Area area = new Area(0, 0, 1, 1);

        // When
        List<Area> subAreas = splitter.split(area);

        // Then
        assertEquals(1, subAreas.size());
        assertEquals(new Area(0, 0, 1, 1), subAreas.get(0));
    }

    @Test
    public void test_split_horizontally() {
        // Given
        Area area = new Area(0, 0, 1, 10);

        // When
        List<Area> subAreas = splitter.split(area)
                .stream()
                .sorted(comparingInt(Area::getY))
                .collect(toList());

        // Then
        assertEquals(2, subAreas.size());
        assertEquals(new Area(0, 0, 1, 5), subAreas.get(0));
        assertEquals(new Area(0, 5, 1, 5), subAreas.get(1));
    }

    @Test
    public void test_split_vertically() {
        // Given
        Area area = new Area(0, 0, 10, 1);

        // When
        List<Area> subAreas = splitter.split(area)
                .stream()
                .sorted(comparingInt(Area::getX))
                .collect(toList());

        // Then
        assertEquals(2, subAreas.size());
        assertEquals(new Area(0, 0, 5, 1), subAreas.get(0));
        assertEquals(new Area(5, 0, 5, 1), subAreas.get(1));
    }

}