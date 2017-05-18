package com.shpota.starwars.entities;

public interface Mortal {

    int getHealth();

    void addHealth(int value);

    default boolean isKilled() {
        return getHealth() <= 0;
    }

    default void kill() {
        addHealth(-getHealth());
    }
}
