package com.shpota.starwars.entities;

import com.shpota.starwars.moves.MoveStrategy;

import static com.shpota.starwars.Constants.GAME_HEIGHT;

public class Bullet extends GameObject {
    private boolean exploded = false;

    public Bullet(int x, int y, String imageName, MoveStrategy strategy) {
        super(x, y, imageName, strategy);
    }

    @Override
    public boolean isActive() {
        return isVisible() && !exploded;
    }

    @Override
    public boolean isVisible() {
        return getY() > 0 && getY() < GAME_HEIGHT;
    }

    public void explode() {
        exploded = true;
    }

    public boolean isExploded() {
        return exploded;
    }
}
