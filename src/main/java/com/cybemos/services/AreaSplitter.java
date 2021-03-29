package com.cybemos.services;

import com.cybemos.model.Area;

import java.util.List;
import java.util.stream.Stream;

public class AreaSplitter {

    public List<Area> split(Area area) {
        if (area.getWidth() <= 1 && area.getHeight() <= 1) {
            return List.of(area);
        }

        int w1 = area.getWidth() / 2;
        int h1 = area.getHeight() / 2;
        int x2 = area.getX() + w1;
        int y2 = area.getY() + h1;
        int w2 = area.getWidth() - w1;
        int h2 = area.getHeight() - h1;

        if (w1 == 0 || w2 == 0) {
            return List.of(
                    new Area(area.getX(), area.getY(), area.getWidth(), h2),
                    new Area(area.getX(), y2, area.getWidth(), h2)
            );
        } else if (h1 == 0 || h2 == 0) {
            return List.of(
                    new Area(area.getX(), area.getY(), w1, area.getHeight()),
                    new Area(x2, area.getY(), w2, area.getHeight())
            );
        } else {
            return List.of(
                    new Area(area.getX(), area.getY(), w1, h1),
                    new Area(x2, area.getY(), w2, h1),
                    new Area(area.getX(), y2, w1, h2),
                    new Area(x2, y2, w2, h2)
            );
        }
    }

}
