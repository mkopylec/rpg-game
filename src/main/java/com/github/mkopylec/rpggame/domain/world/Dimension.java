package com.github.mkopylec.rpggame.domain.world;

import com.github.mkopylec.ddd.buildingblocks.ValueObject;

import static com.google.common.base.Preconditions.checkArgument;

@ValueObject
public class Dimension {

    private final int width;
    private final int height;

    public Dimension(int width, int height) {
        checkArgument(width > 0, "World width must be greater than 0");
        checkArgument(height > 0, "World height must be greater than 0");
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
