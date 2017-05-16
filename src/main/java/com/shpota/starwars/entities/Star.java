package com.shpota.starwars.entities;

import com.shpota.starwars.moves.MoveStrategy;

public class Star extends GameObject {
    public Star(int x, int y, String imageName, MoveStrategy moveStrategy) {
        super(x, y, imageName, moveStrategy);
    }

    @Override
    public boolean isVisible() {
        return true;
    }
}
