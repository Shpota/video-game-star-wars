package com.shpota.starwars.view;

import com.shpota.starwars.entities.GameObject;
import com.shpota.starwars.model.StateListener;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.Collection;

public interface View extends StateListener {
    void renderGamePlay(Collection<GameObject> gameObjects, long score);

    void renderMenu();

    void renderHelp();

    void addKeyListener(KeyListener l);

    void addMouseListener(MouseListener l);

    boolean belongsToNewButton(int x, int y);

    boolean belongsToLoadButton(int x, int y);

    boolean belongsToHelpButton(int x, int y);

    boolean belongsToQuiteButton(int x, int y);
}
