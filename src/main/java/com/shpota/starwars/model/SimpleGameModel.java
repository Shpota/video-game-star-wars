package com.shpota.starwars.model;

import com.shpota.starwars.entities.Bullet;
import com.shpota.starwars.entities.Enemy;
import com.shpota.starwars.entities.GameObject;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

import static com.shpota.starwars.Constants.*;
import static java.util.stream.Collectors.toList;

public class SimpleGameModel extends GameModel {
    private final List<GameObject> gameObjects = new CopyOnWriteArrayList<>();
    private final AtomicLong score;

    public SimpleGameModel(long score) {
        this.score = new AtomicLong(score);
    }

    @Override
    public void process() {
        processUpdates();
        getStateListeners().forEach(
                listener -> listener.on(getState())
        );
    }

    @Override
    public void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    @Override
    public List<GameObject> getGameObjects() {
        return gameObjects.stream()
                .filter(GameObject::isVisible)
                .collect(toList());
    }

    @Override
    public long getScore() {
        return score.get();
    }

    @Override
    public void setScore(long score) {
        this.score.set(score);
    }

    private void processUpdates() {
        gameObjects.forEach(GameObject::update);
        List<Enemy> enemies = gameObjects.stream()
                .filter(GameObject::isVisible)
                .filter(Enemy.class::isInstance)
                .map(gameObject -> (Enemy) gameObject)
                .collect(toList());

        List<Bullet> bullets = gameObjects.stream()
                .filter(GameObject::isVisible)
                .filter(Bullet.class::isInstance)
                .map(gameObject -> (Bullet) gameObject)
                .collect(toList());

        for (Enemy enemy : enemies) {
            Optional<Bullet> bulletOptional = bullets.stream()
                    .filter(bullet -> intersects(bullet, enemy))
                    .findFirst();

            if (bulletOptional.isPresent()) {
                enemy.kill();
                Bullet bullet = bulletOptional.get();
                bullet.explode();
                score.addAndGet(SCORE_INCREMENTER);
            }
        }

        removeInactiveGameObjects();
    }

    private void removeInactiveGameObjects() {
        List<GameObject> inActiveObjects = gameObjects.stream()
                .filter(object -> !object.isActive())
                .collect(toList());
        gameObjects.removeAll(inActiveObjects);
    }

    private boolean intersects(Bullet bullet, Enemy enemy) {
        return bullet.getX() < enemy.getX() + ENEMY_WIDTH
                && bullet.getX() + BULLET_WIDTH > enemy.getX()
                && bullet.getY() < enemy.getY() + ENEMY_HEIGHT
                && bullet.getY() + BULLET_HEIGHT > enemy.getY();
    }
}
