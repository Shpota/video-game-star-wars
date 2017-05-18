package com.shpota.starwars.entities;

import com.shpota.starwars.moves.MoveStrategy;

public class Player extends GameObject implements Mortal {
    private int health = 100;

    public Player(int x, int y, String imageName, MoveStrategy strategy) {
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
    public boolean isVisible() {
        return !isKilled();
    }

    @Override
    public boolean isActive() {
        return !isKilled();
    }
}
