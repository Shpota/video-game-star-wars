package com.shpota.starwars;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public interface Constants {
    String GAME_TITLE = "Star Wars";
    int GAME_WIDTH = 700;
    int GAME_HEIGHT = 700;
    int PLAYER_WIDTH = 32;
    int PLAYER_HEIGHT = 32;
    int PLAYER_START_POSITION_X = GAME_WIDTH / 2 - (PLAYER_WIDTH / 2);
    int PLAYER_START_POSITION_Y = GAME_HEIGHT - (PLAYER_HEIGHT * 3);
    int STAR_HEIGHT = 5;
    int STAR_WIDTH = 5;
    int ENEMY_HEIGHT = 32;
    int ENEMY_WIDTH = 32;
    int BULLET_WIDTH = 9;
    int BULLET_HEIGHT = 16;
    int BULLET_STEP = 15;
    int BULLET_POSITION_RELATIVE_TO_PLAYER = 11;

    int SCORE_INCREMENTER = 10;
    int SLEEP_INTERVAL = 20;

    int BUTTON_INTERVAL = GAME_HEIGHT / 9;
    BufferedImage BACKGROUND = new BufferedImage(
            GAME_WIDTH, GAME_HEIGHT, TYPE_INT_RGB
    );
    Rectangle NEW_GAME_BUTTON = new Rectangle(
            GAME_WIDTH / 3, BUTTON_INTERVAL * 3, GAME_WIDTH / 3, 50
    );
    Rectangle LOAD_BUTTON = new Rectangle(
            GAME_WIDTH / 3, BUTTON_INTERVAL * 4, GAME_WIDTH / 3, 50
    );
    Rectangle HELP_BUTTON = new Rectangle(
            GAME_WIDTH / 3, BUTTON_INTERVAL * 5, GAME_WIDTH / 3, 50
    );
    Rectangle QUITE_BUTTON = new Rectangle(
            GAME_WIDTH / 3, BUTTON_INTERVAL * 6, GAME_WIDTH / 3, 50
    );
}
