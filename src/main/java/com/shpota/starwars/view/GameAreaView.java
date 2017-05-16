package com.shpota.starwars.view;

import com.shpota.starwars.entities.GameObject;
import com.shpota.starwars.io.FileSystemImageLoader;
import com.shpota.starwars.io.ImageLoader;
import com.shpota.starwars.model.GameModel;
import com.shpota.starwars.model.State;
import com.shpota.starwars.util.Assert;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.shpota.starwars.Constants.*;
import static java.awt.Color.BLUE;
import static java.awt.Color.GREEN;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class GameAreaView extends Canvas implements View {
    private final Map<String, BufferedImage> images;
    private final GameModel gameModel;

    private GameAreaView(GameModel gameModel, Map<String, BufferedImage> images) {
        this.images = images;
        this.gameModel = gameModel;
    }

    public static GameAreaView initialize(GameModel gameModel) {
        Assert.notNull(gameModel, "gameModel must not be null");
        ImageLoader imageLoader = new FileSystemImageLoader();
        Map<String, BufferedImage> images = new HashMap<>();
        images.put("ship", imageLoader.loadImage("/ship.png"));
        images.put("bullet", imageLoader.loadImage("/bullet.png"));
        images.put("blue_star", imageLoader.loadImage("/blue_star.png"));
        images.put("white_star", imageLoader.loadImage("/white_star.png"));
        images.put("enemy", imageLoader.loadImage("/enemy.png"));
        images.put("earth", imageLoader.loadImage("/earth.png"));
        GameAreaView gameArea = new GameAreaView(gameModel, images);
        gameArea.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        JFrame window = new JFrame(GAME_TITLE);
        window.add(gameArea);
        window.pack();
        window.setResizable(false);
        window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        window.setLocationRelativeTo(null);
        gameArea.createBufferStrategy(3);
        gameArea.requestFocus();
        gameModel.addSateListener(gameArea);
        window.setVisible(true);
        return gameArea;
    }

    @Override
    public void on(State state) {
        switch (state) {
            case GAME:
                renderGamePlay(gameModel.getGameObjects(), gameModel.getScore());
                break;
            case MENU:
                renderMenu();
                break;
            case HELP:
                renderHelp();
                break;
        }
    }

    @Override
    public void renderGamePlay(Collection<GameObject> gameObjects, long score) {
        BufferStrategy bufferStrategy = getBufferStrategy();
        Graphics graphics = bufferStrategy.getDrawGraphics();

        graphics.drawImage(BACKGROUND, 0, 0, getWidth(), getHeight(), this);

        for (GameObject gameObject : gameObjects) {
            Image image = images.get(gameObject.getImageName());
            graphics.drawImage(image, gameObject.getX(), gameObject.getY(), this);
        }
        renderScore((Graphics2D) graphics, score);

        graphics.dispose();
        bufferStrategy.show();
    }

    @Override
    public void renderMenu() {
        BufferStrategy bufferStrategy = getBufferStrategy();
        Graphics graphics = bufferStrategy.getDrawGraphics();

        graphics.drawImage(BACKGROUND, 0, 0, getWidth(), getHeight(), this);
        BufferedImage earth = images.get("earth");
        graphics.drawImage(earth, 0, GAME_HEIGHT - earth.getHeight(), earth.getWidth(), earth.getHeight(), this);

        renderButtons((Graphics2D) graphics);

        graphics.dispose();
        bufferStrategy.show();
    }

    // TODO: the following methods need to be expressed in more clean way
    @Override
    public void renderHelp() {
        BufferStrategy bufferStrategy = getBufferStrategy();
        Graphics graphics = bufferStrategy.getDrawGraphics();

        graphics.drawImage(BACKGROUND, 0, 0, getWidth(), getHeight(), this);

        BufferedImage earth = images.get("earth");
        graphics.drawImage(earth, 0, GAME_HEIGHT - earth.getHeight(), earth.getWidth(), earth.getHeight(), this);

        Font buttonFont = new Font("arial", Font.PLAIN, 30);
        graphics.setFont(buttonFont);
        graphics.setColor(GREEN);
        int distance = GAME_HEIGHT / 20;

        graphics.drawString("ESC:", GAME_WIDTH / 7, distance * 7);
        graphics.drawString("Return to main menu", GAME_WIDTH / 3, distance * 7);
        graphics.drawString("S:", GAME_WIDTH / 7, distance * 8);
        graphics.drawString("Save game", GAME_WIDTH / 3, distance * 8);
        graphics.drawString("Arrows:", GAME_WIDTH / 7, distance * 9);
        graphics.drawString("Control player position", GAME_WIDTH / 3, distance * 9);
        graphics.drawString("Space:", GAME_WIDTH / 7, distance * 10);
        graphics.drawString("Shutting", GAME_WIDTH / 3, distance * 10);

        graphics.dispose();
        bufferStrategy.show();
    }


    private void renderButtons(Graphics2D graphics) {
        Font font = new Font("arial", Font.BOLD, 50);
        graphics.setFont(font);
        graphics.setColor(BLUE);
        graphics.drawString(GAME_TITLE, GAME_WIDTH / 3, BUTTON_INTERVAL * 3 - 30);

        Font buttonFont = new Font("arial", Font.BOLD, 30);
        graphics.setFont(buttonFont);
        graphics.setColor(GREEN);
        graphics.drawString("New Game", (int) (NEW_GAME_BUTTON.getX() + 10), (float) (NEW_GAME_BUTTON.getY() + 35));
        graphics.drawString("Load Game", (int) (LOAD_BUTTON.getX() + 10), (float) (LOAD_BUTTON.getY() + 35));
        graphics.drawString("Help", (int) (HELP_BUTTON.getX() + 10), (float) (HELP_BUTTON.getY() + 35));
        graphics.drawString("Quit", (int) (QUITE_BUTTON.getX() + 10), (float) (QUITE_BUTTON.getY() + 35));

        graphics.draw(NEW_GAME_BUTTON);
        graphics.draw(LOAD_BUTTON);
        graphics.draw(HELP_BUTTON);
        graphics.draw(QUITE_BUTTON);
    }


    private void renderScore(Graphics2D graphics, long score) {
        Font buttonFont = new Font("arial", Font.BOLD, 20);
        graphics.setFont(buttonFont);
        graphics.setColor(GREEN);
        Rectangle scoreBox = new Rectangle(10, 10, GAME_WIDTH / 5, 40);
        graphics.draw(scoreBox);
        graphics.drawString("SCORE: " + score, 15, 40);
    }

    @Override
    public boolean belongsToNewButton(int x, int y) {
        return pointBelongsTo(x, y, NEW_GAME_BUTTON);
    }

    @Override
    public boolean belongsToLoadButton(int x, int y) {
        return pointBelongsTo(x, y, LOAD_BUTTON);
    }

    @Override
    public boolean belongsToHelpButton(int x, int y) {
        return pointBelongsTo(x, y, HELP_BUTTON);
    }

    @Override
    public boolean belongsToQuiteButton(int x, int y) {
        return pointBelongsTo(x, y, QUITE_BUTTON);
    }

    private boolean pointBelongsTo(int x, int y, Rectangle rectangle) {
        return (rectangle.getX() < x && rectangle.getY() < y &&
                rectangle.getX() + rectangle.getWidth() > x &&
                rectangle.getY() + rectangle.getHeight() > y);
    }
}
