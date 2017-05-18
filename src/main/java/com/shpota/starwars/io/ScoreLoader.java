package com.shpota.starwars.io;

public interface ScoreLoader {

    long loadScore();

    void saveScore(long score);
}
