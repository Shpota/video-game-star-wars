package com.shpota.starwars.moves;

import com.shpota.starwars.util.Assert;

import java.util.Random;

import static com.shpota.starwars.Constants.GAME_HEIGHT;
import static com.shpota.starwars.Constants.GAME_WIDTH;

public class MoveDownWithReturnStrategy implements MoveStrategy {
    private static final Random RANDOM = new Random();
    private final int step;
    private final int gameObjectHeight;
    private final int gameObjectWidth;

    public MoveDownWithReturnStrategy(int step, int gameObjectWidth, int gameObjectHeight) {
        Assert.isTrue(step > 0, "Step must be greater than 0");
        Assert.isTrue(gameObjectWidth > 0, "Width must be greater than 0");
        this.step = step;
        this.gameObjectHeight = gameObjectHeight;
        this.gameObjectWidth = gameObjectWidth;
    }

    @Override
    public Point next(Point point) {
        int x = point.getX();
        int y = point.getY() + step;

        if (point.getY() > GAME_HEIGHT) {
            x = RANDOM.nextInt(GAME_WIDTH - gameObjectWidth);
            y = -gameObjectHeight;
        }

        return new Point(x, y);
    }
}
