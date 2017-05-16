package com.shpota.starwars.entities;

import com.shpota.starwars.moves.MoveStrategy;
import com.shpota.starwars.moves.Point;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class PlayerTest {

    @Test
    void shouldAddHealth() {
        MoveStrategy strategy = strategyStub();
        Player player = new Player(1, 5, "test", strategy);
        Integer expectedHealth = player.getHealth() + 50;

        player.addHealth(50);

        Integer actualHealth = player.getHealth();
        assertThat(actualHealth, is(expectedHealth));

    }

    @Test
    void shouldBeVisible() {
        MoveStrategy strategy = strategyStub();
        Player player = new Player(1, 5, "test", strategy);

        boolean visible = player.isVisible();

        assertThat(visible, is(true));
    }

    @Test
    void shouldNotBeVisible() {
        MoveStrategy strategy = strategyStub();
        Player player = new Player(1, 5, "test", strategy);
        player.kill();

        boolean visible = player.isVisible();

        assertThat(visible, is(false));
    }

    @Test
    void shouldBeActive() {
        MoveStrategy strategy = strategyStub();
        Player player = new Player(1, 5, "test", strategy);

        boolean active = player.isActive();

        assertThat(active, is(true));
    }

    @Test
    void shouldNotBeActive() {
        MoveStrategy strategy = strategyStub();
        Player player = new Player(1, 5, "test", strategy);
        player.kill();

        boolean active = player.isActive();

        assertThat(active, is(false));
    }

    private MoveStrategy strategyStub() {
        return new MoveStrategy() {
            public Point next(Point point) {
                return point;
            }

        };
    }
}