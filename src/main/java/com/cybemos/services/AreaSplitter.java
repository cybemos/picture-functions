package com.cybemos.services;

import com.cybemos.model.Area;

import java.util.List;

public class AreaSplitter {

    /**
     * Split the area into 4 parts if possible.
     * If {@link Area#getWidth()} > 1 and {@link Area#getHeight()} == 1, then split into 2 parts.
     * If {@link Area#getWidth()} == 1 and {@link Area#getHeight()} > 1, then split into 2 parts.
     * If {@link Area#getWidth()} == 1 and {@link Area#getHeight()} )) 1, then return area is the same as input.
     */
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
                    Area.of(area.getX(), area.getY(), area.getWidth(), h2),
                    Area.of(area.getX(), y2, area.getWidth(), h2)
            );
        } else if (h1 == 0 || h2 == 0) {
            return List.of(
                    Area.of(area.getX(), area.getY(), w1, area.getHeight()),
                    Area.of(x2, area.getY(), w2, area.getHeight())
            );
        } else {
            return List.of(
                    Area.of(area.getX(), area.getY(), w1, h1),
                    Area.of(x2, area.getY(), w2, h1),
                    Area.of(area.getX(), y2, w1, h2),
                    Area.of(x2, y2, w2, h2)
            );
        }
    }

}
