package com.shpota.starwars.moves;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class MoveUpStrategyTest {
    @Test
    void shouldGetNext() {
        int step = 10;
        int x = 11;
        int y = 15;
        MoveUpStrategy strategy = new MoveUpStrategy(step);
        Point input = new Point(x, y);
        Point expectedResult = new Point(x, y - step);

        Point result = strategy.next(input);

        assertThat(result, is(expectedResult));
    }
}