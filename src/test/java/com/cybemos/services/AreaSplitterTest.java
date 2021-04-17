package com.cybemos.services;

import com.cybemos.model.Area;
import org.junit.Test;

import java.util.List;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

public class AreaSplitterTest {

    private final AreaSplitter splitter = new AreaSplitter();

    @Test
    public void when_area_is_2x2_then_output_is_4_area_of_dimensions_1x1() {
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
    public void when_area_is_1x1_then_output_is_an_area_of_dimensions_1x1() {
        // Given
        Area area = new Area(0, 0, 1, 1);

        // When
        List<Area> subAreas = splitter.split(area);

        // Then
        assertEquals(1, subAreas.size());
        assertEquals(new Area(0, 0, 1, 1), subAreas.get(0));
    }

    @Test
    public void when_area_is_1x10_then_output_is_2_areas_of_dimensions_1x5() {
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
    public void when_area_is_10x1_then_output_is_2_areas_of_dimensions_5x1() {
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