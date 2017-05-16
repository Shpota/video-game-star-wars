package com.shpota.starwars.moves;

import static com.shpota.starwars.Constants.*;

public class UserControlMoveStrategy implements MoveStrategy {
    private int deltaX = 0;
    private int deltaY = 0;

    @Override
    public Point next(Point point) {
        int x = point.getX() + deltaX;
        int y = point.getY() + deltaY;

        if (x < 0) {
            x = 0;
        } else if (x > GAME_WIDTH - PLAYER_WIDTH) {
            x = GAME_WIDTH - PLAYER_WIDTH;
        }

        if (y < 0) {
            y = 0;
        } else if (y > GAME_HEIGHT - PLAYER_HEIGHT) {
            y = GAME_HEIGHT - PLAYER_HEIGHT;
        }

        return new Point(x, y);
    }

    public void addDeltaX(int value) {
        deltaX += value;
    }

    public void addDeltaY(int value) {
        deltaY += value;
    }

    public void clearDeltaX() {
        deltaX = 0;
    }

    public void clearDeltaY() {
        deltaY = 0;
    }
}
