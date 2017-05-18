package com.shpota.starwars.view;

import com.shpota.starwars.model.GameModel;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.mock;

class GameAreaViewTest {
    @Test
    void shouldInitialize() {
        GameModel gameModel = mock(GameModel.class);

        GameAreaView view = GameAreaView.initialize(gameModel);

        assertThat(view, is(notNullValue()));
    }
}