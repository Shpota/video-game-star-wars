package com.shpota.starwars.entities;

import com.shpota.starwars.moves.MoveStrategy;
import com.shpota.starwars.moves.Point;
import com.shpota.starwars.util.Assert;

public abstract class GameObject {
    private int x;
    private int y;
    private String imageName;
    private MoveStrategy strategy;

    public GameObject(int x, int y, String imageName, MoveStrategy strategy) {
        Assert.notNull(imageName, "imageName must not be null");
        Assert.notNull(strategy, "strategy must not be null");
        this.x = x;
        this.y = y;
        this.imageName = imageName;
        this.strategy = strategy;
    }

    public void update() {
        Point point = strategy.next(new Point(x, y));
        x = point.getX();
        y = point.getY();
    }

    public boolean isActive() {
        return true;
    }

    public abstract boolean isVisible();

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getImageName() {
        return imageName;
    }
}
