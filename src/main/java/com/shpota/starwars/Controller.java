package com.shpota.starwars;

import com.shpota.starwars.entities.Bullet;
import com.shpota.starwars.entities.Enemy;
import com.shpota.starwars.entities.Player;
import com.shpota.starwars.entities.Star;
import com.shpota.starwars.io.FileSystemScoreLoader;
import com.shpota.starwars.io.ScoreLoader;
import com.shpota.starwars.model.GameModel;
import com.shpota.starwars.model.SimpleGameModel;
import com.shpota.starwars.moves.MoveDownWithReturnStrategy;
import com.shpota.starwars.moves.MoveUpStrategy;
import com.shpota.starwars.moves.UserControlMoveStrategy;
import com.shpota.starwars.view.GameAreaView;
import com.shpota.starwars.view.View;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import static com.shpota.starwars.Constants.*;
import static com.shpota.starwars.model.State.*;
import static java.awt.event.KeyEvent.*;
import static java.util.stream.IntStream.range;

public class Controller {
    private static final Random RANDOM = new Random();
    private final GameModel gameModel;
    private final View view;
    private final ScoreLoader scoreLoader;

    private Controller(GameModel gameModel, View view, ScoreLoader scoreLoader) {
        this.gameModel = gameModel;
        this.view = view;
        this.scoreLoader = scoreLoader;
    }

    public static void main(String[] args) {
        Controller initialize = Controller.initialize();
        initialize.startGame();
    }

    private void startGame() {
        gameModel.start();
    }

    private static Controller initialize() {
        ScoreLoader scoreLoader = FileSystemScoreLoader.initialize("./score.txt");
        GameModel gameModel = new SimpleGameModel(0);
        UserControlMoveStrategy moveStrategy = new UserControlMoveStrategy();
        Player player = createPlayer(moveStrategy);
        gameModel.addGameObject(player);
        range(0, 5).forEach(val -> gameModel.addGameObject(createStar("blue_star")));
        range(0, 10).forEach(val -> gameModel.addGameObject(createStar("white_star")));
        addEnemies(gameModel);

        View view = GameAreaView.initialize(gameModel);
        Controller controller = new Controller(gameModel, view, scoreLoader);

        controller.loadingListener();
        controller.escapeButtonListener();
        controller.moveListener(moveStrategy);
        controller.bulletShutListener(player);
        controller.menuListener(player);

        return controller;
    }

    private static Player createPlayer(UserControlMoveStrategy moveStrategy) {
        return new Player(
                PLAYER_START_POSITION_X,
                PLAYER_START_POSITION_Y,
                "ship",
                moveStrategy
        );
    }

    private static void addEnemies(GameModel gameModel) {
        range(0, 5).forEach(val -> gameModel.addGameObject(new Enemy(
                RANDOM.nextInt(GAME_WIDTH - ENEMY_WIDTH),
                RANDOM.nextInt(GAME_HEIGHT) - GAME_HEIGHT,
                "enemy",
                new MoveDownWithReturnStrategy(
                        2, ENEMY_WIDTH, ENEMY_HEIGHT
                )
        )));
    }

    private static Star createStar(String star) {
        return new Star(
                RANDOM.nextInt(GAME_WIDTH - STAR_WIDTH),
                RANDOM.nextInt(GAME_HEIGHT),
                star,
                new MoveDownWithReturnStrategy(
                        5, STAR_WIDTH, STAR_HEIGHT
                )
        );
    }

    private void moveListener(UserControlMoveStrategy strategy) {
        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                switch (evt.getKeyCode()) {
                    case VK_DOWN:
                        strategy.addDeltaY(10);
                        break;
                    case VK_UP:
                        strategy.addDeltaY(-10);
                        break;
                    case VK_LEFT:
                        strategy.addDeltaX(-10);
                        break;
                    case VK_RIGHT:
                        strategy.addDeltaX(10);
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent evt) {
                switch (evt.getKeyCode()) {
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_UP:
                        strategy.clearDeltaY();
                        break;
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_RIGHT:
                        strategy.clearDeltaX();
                        break;
                }
            }
        });
    }

    private void loadingListener() {
        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == VK_S && gameModel.getState() == GAME) {
                    scoreLoader.saveScore(gameModel.getScore());
                }
            }
        });
    }

    private void escapeButtonListener() {
        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (VK_ESCAPE == evt.getKeyCode()) {
                    gameModel.setState(MENU);
                }
            }
        });
    }

    private void bulletShutListener(Player player) {
        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (VK_SPACE == evt.getKeyCode()) {
                    gameModel.addGameObject(new Bullet(
                            player.getX() + BULLET_POSITION_RELATIVE_TO_PLAYER,
                            player.getY(),
                            "bullet",
                            new MoveUpStrategy(BULLET_STEP)
                    ));
                }
            }
        });
    }

    private void menuListener(Player player) {
        view.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (gameModel.getState() != MENU) {
                    return;
                }
                int x = e.getX();
                int y = e.getY();
                if (view.belongsToNewButton(x, y)) {
                    gameModel.setScore(0);
                    gameModel.setState(GAME);
                } else if (view.belongsToLoadButton(x, y)) {
                    gameModel.setState(GAME);
                    long score = scoreLoader.loadScore();
                    gameModel.setScore(score);
                    player.setX(PLAYER_START_POSITION_X);
                    player.setY(PLAYER_START_POSITION_Y);
                } else if (view.belongsToHelpButton(x, y)) {
                    gameModel.setState(HELP);
                } else if (view.belongsToQuiteButton(x, y)) {
                    System.exit(0);
                }
            }
        });
    }
}
