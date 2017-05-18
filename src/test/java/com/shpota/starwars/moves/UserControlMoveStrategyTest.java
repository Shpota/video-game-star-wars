package com.shpota.starwars.moves;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.shpota.starwars.Constants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.params.provider.ObjectArrayArguments.create;

class UserControlMoveStrategyTest {
    @ParameterizedTest
    @MethodSource(names = "pointsProvider")
    void shouldGetNext(Point input, int deltaX, int deltaY, Point expectedResult) {
        UserControlMoveStrategy strategy = new UserControlMoveStrategy();
        strategy.addDeltaX(deltaX);
        strategy.addDeltaY(deltaY);

        Point result = strategy.next(input);

        assertThat(result, is(expectedResult));
    }

    static Stream<Arguments> pointsProvider() {
        return Stream.of(
                create(point(1, 2), 5, 6, point(6, 8)),
                create(point(1, 2), -5, 0, point(0, 2)),
                create(point(GAME_WIDTH - 100, 2), 200, 0, point(GAME_WIDTH - PLAYER_WIDTH, 2)),
                create(point(1, 2), 5, -6, point(6, 0)),
                create(point(0, GAME_HEIGHT - PLAYER_HEIGHT), 0, 10, point(0, GAME_HEIGHT - PLAYER_HEIGHT))
        );
    }

    private static Point point(int x, int y) {
        return new Point(x, y);
    }
}