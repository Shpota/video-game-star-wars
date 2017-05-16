package com.shpota.starwars.entities;

import com.shpota.starwars.moves.MoveStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.shpota.starwars.Constants.GAME_HEIGHT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.params.provider.ObjectArrayArguments.create;

class BulletTest {
    @Test
    void shouldBeActive() {
        MoveStrategy moveStrategy = p -> p;
        Bullet bullet = new Bullet(1, 2, "test", moveStrategy);

        boolean actualActive = bullet.isActive();

        assertThat(actualActive, is(true));
    }

    @Test
    void shouldNotBeActiveGivenExplodedBullet() {
        MoveStrategy moveStrategy = p -> p;
        Bullet bullet = new Bullet(1, 2, "test", moveStrategy);
        bullet.explode();

        boolean actualActive = bullet.isActive();

        assertThat(actualActive, is(false));
    }

    @Test
    void shouldNotBeActiveGivenNegativeCoordinate() {
        MoveStrategy moveStrategy = p -> p;
        Bullet bullet = new Bullet(-1, -2, "test", moveStrategy);

        boolean active = bullet.isActive();

        assertThat(active, is(false));
    }

    @ParameterizedTest
    @MethodSource(names = "visibilityTestProvider")
    void shouldBeVisible(int y, boolean result) {
        MoveStrategy moveStrategy = p -> p;
        Bullet bullet = new Bullet(1, y, "test", moveStrategy);

        boolean visible = bullet.isVisible();

        assertThat(visible, is(result));
    }

    static Stream<Arguments> visibilityTestProvider() {
        return Stream.of(
                create(1, true),
                create(-1, false),
                create(0, false),
                create(GAME_HEIGHT, false),
                create(GAME_HEIGHT + 1, false),
                create(GAME_HEIGHT - 1, true)
        );
    }

    @Test
    void shouldExplode() {
        MoveStrategy moveStrategy = p -> p;
        Bullet bullet = new Bullet(1, 1, "test", moveStrategy);

        bullet.explode();

        boolean exploded = bullet.isExploded();
        assertThat(exploded, is(true));
    }
}