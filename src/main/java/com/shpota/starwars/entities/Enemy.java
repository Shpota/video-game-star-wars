package com.shpota.starwars.entities;

import com.shpota.starwars.moves.MoveStrategy;

import static com.shpota.starwars.Constants.GAME_HEIGHT;

public class Enemy extends GameObject implements Mortal {
    private int health = 100;

    public Enemy(int x, int y, String imageName, MoveStrategy strategy) {
        super(x, y, imageName, strategy);
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void addHealth(int value) {
        health += value;
    }

    @Override
    public void update() {
        super.update();
        //resurrect enemy once it appears on the screen
        if (getY() > GAME_HEIGHT) {
            addHealth(100 - getHealth());
        }
    }

    @Override
    public boolean isVisible() {
        return !isKilled();
    }
}
