package com.shpota.starwars.moves;

import org.junit.jupiter.api.Test;

import static com.shpota.starwars.Constants.GAME_HEIGHT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class MoveDownWithReturnStrategyTest {
    @Test
    void shouldGetNext() {
        MoveStrategy strategy = new MoveDownWithReturnStrategy(10, 5, 8);
        Point point = new Point(7, 8);
        Point expectedResult = new Point(7, 18);

        Point result = strategy.next(point);

        assertThat(result, is(expectedResult));
    }

    @Test
    void shouldGetNextGivenPointUnderGameArea() {
        int gameObjectHeight = 8;
        MoveDownWithReturnStrategy strategy = new MoveDownWithReturnStrategy(10, 5, gameObjectHeight);
        int x = 10;
        Point point = new Point(x, GAME_HEIGHT + 10);

        Point result = strategy.next(point);

        assertThat(result, allOf(
                hasProperty("x", is(not(x))),
                hasProperty("y", is(-gameObjectHeight))
        ));
    }
}