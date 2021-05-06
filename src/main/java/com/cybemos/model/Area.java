package com.cybemos.model;

import lombok.Value;

/**
 * @implSpec This class is immutable and thread-safe.
 */
@Value(staticConstructor = "of")
public class Area {

    int x;
    int y;
    int width;
    int height;

}
