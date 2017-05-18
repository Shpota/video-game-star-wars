package com.shpota.starwars.moves;

import com.shpota.starwars.util.Assert;

public class MoveUpStrategy implements MoveStrategy {
    private final int step;

    public MoveUpStrategy(int step) {
        Assert.isTrue(step > 0, "Step must be greater than 0");
        this.step = step;
    }

    @Override
    public Point next(Point point) {
        return new Point(point.getX(), point.getY() - step);
    }
}
