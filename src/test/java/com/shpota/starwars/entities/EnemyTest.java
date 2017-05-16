package com.shpota.starwars.entities;

import com.shpota.starwars.moves.MoveStrategy;
import org.junit.jupiter.api.Test;

import static com.shpota.starwars.Constants.GAME_HEIGHT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

class EnemyTest {

    @Test
    void shouldAddHealth() {
        MoveStrategy strategy = mock(MoveStrategy.class);
        Enemy enemy = new Enemy(1, 2, "test", strategy);
        int health = enemy.getHealth();

        enemy.addHealth(14);

        int updateHealth = enemy.getHealth();
        assertThat(updateHealth, is(health + 14));
    }

    @Test
    void shouldUpdateHealthGivenLocationUnderTheGameArea() {
        MoveStrategy strategy = point -> point;
        Enemy enemy = new Enemy(1, GAME_HEIGHT + 1, "test", strategy);
        enemy.addHealth(15);

        enemy.update();

        assertThat(enemy.getHealth(), is(100));
    }

    @Test
    void shouldBeVisible() {
        MoveStrategy strategy = mock(MoveStrategy.class);
        Enemy enemy = new Enemy(1, GAME_HEIGHT + 1, "test", strategy);

        boolean visible = enemy.isVisible();

        assertThat(visible, is(true));
    }

    @Test
    void shouldNotBeVisibleGivenKilledEnemy() {
        MoveStrategy strategy = mock(MoveStrategy.class);
        Enemy enemy = new Enemy(1, GAME_HEIGHT + 1, "test", strategy);
        enemy.kill();

        boolean visible = enemy.isVisible();

        assertThat(visible, is(false));
    }
}