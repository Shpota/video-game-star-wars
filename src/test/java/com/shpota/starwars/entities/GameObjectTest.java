package com.shpota.starwars.entities;

import com.shpota.starwars.moves.MoveStrategy;
import com.shpota.starwars.moves.Point;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GameObjectTest {
    @Test
    void shouldUpdate() {
        MoveStrategy strategy = mock(MoveStrategy.class);
        given(strategy.next(new Point(1, 2))).willReturn(new Point(5, 10));
        GameObject gameObject = new SimpleGameObject(1, 2, "test", strategy);

        gameObject.update();

        assertThat(gameObject, allOf(
                hasProperty("x", is(5)),
                hasProperty("y", is(10))
        ));
    }

    static class SimpleGameObject extends GameObject {
        SimpleGameObject(int x, int y, String imageName, MoveStrategy strategy) {
            super(x, y, imageName, strategy);
        }

        @Override
        public boolean isVisible() {
            return false;
        }
    }
}