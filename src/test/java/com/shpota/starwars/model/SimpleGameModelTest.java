package com.shpota.starwars.model;

import com.shpota.starwars.entities.Bullet;
import com.shpota.starwars.entities.Enemy;
import com.shpota.starwars.entities.GameObject;
import com.shpota.starwars.entities.Star;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.shpota.starwars.Constants.GAME_HEIGHT;
import static com.shpota.starwars.Constants.SCORE_INCREMENTER;
import static com.shpota.starwars.model.State.GAME;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class SimpleGameModelTest {

    @Test
    void shouldGetGameObjects() {
        SimpleGameModel model = new SimpleGameModel(0);
        Star star = new Star(1, 2, "test", point -> point);
        Bullet bullet = new Bullet(1, 2, "test", point -> point);
        Bullet invisibleBullet = new Bullet(1, GAME_HEIGHT + 10, "test", point -> point);
        model.addGameObject(star);
        model.addGameObject(bullet);
        model.addGameObject(invisibleBullet);

        List<GameObject> gameObjects = model.getGameObjects();

        assertThat(gameObjects, contains(star, bullet));
    }

    @Test
    void shouldProcessEnemy() {
        SimpleGameModel model = new SimpleGameModel(0);
        Bullet bullet = new Bullet(1, 2, "test", point -> point);
        Enemy enemy = new Enemy(1, 2, "test", point -> point);
        model.addGameObject(bullet);
        model.addGameObject(enemy);

        model.process();

        List<GameObject> gameObjects = model.getGameObjects();
        assertThat(gameObjects, is(empty()));
    }

    @Test
    void shouldInvokeListeners() {
        SimpleGameModel model = new SimpleGameModel(0);
        model.setState(GAME);
        StateListener listener = mock(StateListener.class);
        model.addSateListener(listener);

        model.process();

        verify(listener).on(GAME);
    }

    @Test
    void shouldIncrementScore() {
        SimpleGameModel model = new SimpleGameModel(12);
        Bullet bullet = new Bullet(1, 2, "test", point -> point);
        Enemy enemy = new Enemy(1, 2, "test", point -> point);
        model.addGameObject(bullet);
        model.addGameObject(enemy);
        long expectedScore = 12 + SCORE_INCREMENTER;

        model.process();

        long score = model.getScore();
        assertThat(score, is(expectedScore));
    }

    @Test
    void shouldRemoveInactiveObjects() {
        SimpleGameModel model = new SimpleGameModel(12);
        GameObject activeObject = mock(GameObject.class);
        given(activeObject.isActive()).willReturn(true);
        given(activeObject.isVisible()).willReturn(true);
        GameObject inactiveObject = mock(GameObject.class);
        given(inactiveObject.isVisible()).willReturn(true);
        model.addGameObject(activeObject);
        model.addGameObject(inactiveObject);

        model.process();

        List<GameObject> gameObjects = model.getGameObjects();
        assertThat(gameObjects, is(singletonList(activeObject)));
    }
}