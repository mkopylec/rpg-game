package com.github.mkopylec.rpggame.domain.world;

import com.github.mkopylec.ddd.buildingblocks.ValueObject;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import static com.google.common.base.Preconditions.checkArgument;

@ValueObject
public class Location {

    private int x;
    private int y;

    public Location() {
        this(0, 0);
    }

    public Location(int x, int y) {
        checkArgument(x >= 0, "X location coordinate must be a positive value");
        checkArgument(y >= 0, "Y location coordinate must be a positive value");
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Location)) return false;

        Location location = (Location) o;

        return new EqualsBuilder()
                .append(x, location.x)
                .append(y, location.y)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(x)
                .append(y)
                .toHashCode();
    }
}
