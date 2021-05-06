package com.cybemos.model;

import lombok.NonNull;
import lombok.Value;

/**
 * @implSpec This class is immutable and thread-safe.
 */
@Value
public class ColorPosition {

    int x;
    int y;
    @NonNull Color color;

}
