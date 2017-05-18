package com.shpota.starwars.model;

import com.shpota.starwars.entities.GameObject;
import com.shpota.starwars.exception.GameException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.shpota.starwars.Constants.SLEEP_INTERVAL;
import static com.shpota.starwars.model.State.MENU;

public abstract class GameModel {
    private final Set<StateListener> stateListeners = new HashSet<>();
    private State state = MENU;

    public final void start() {
        new Thread(this::gameLoop).start();
    }

    private void gameLoop() {
        while (true) {
            long startTime = System.currentTimeMillis();

            process();

            long timeAfterRender = System.currentTimeMillis();
            long remaining = SLEEP_INTERVAL - (timeAfterRender - startTime);
            if (remaining > 0) {
                sleep(remaining);
            }
        }
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void addSateListener(StateListener stateListener) {
        stateListeners.add(stateListener);
    }

    final Set<StateListener> getStateListeners() {
        return stateListeners;
    }

    public abstract void process();

    public abstract void addGameObject(GameObject gameObject);

    public abstract List<GameObject> getGameObjects();

    public abstract long getScore();

    public abstract void setScore(long score);

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new GameException(e);
        }
    }
}
