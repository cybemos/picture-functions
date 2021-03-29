package com.cybemos.model;

import lombok.NonNull;
import lombok.Value;

@Value
public class ColorPosition {

    int x;
    int y;
    @NonNull Color color;

}
