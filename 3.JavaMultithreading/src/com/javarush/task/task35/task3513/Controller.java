package com.javarush.task.task35.task3513;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Serhii Boiko on 15.07.2017.
 */
public class Controller extends KeyAdapter {
    private static final int WINNING_TILE = 2048;
    private Model model;
    private View view;

    public Controller(Model model) {
        this.model = model;
        this.view = new View(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            view.arrow = null;
            resetGame();
        }
        if (!model.canMove()) view.isGameLost = true;
        if (!view.isGameLost & !view.isGameWon) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    view.arrow = Arrow.LEFT;
                    model.left();
                    break;
                case KeyEvent.VK_RIGHT:
                    view.arrow = Arrow.RIGHT;
                    model.right();
                    break;
                case KeyEvent.VK_UP:
                    view.arrow = Arrow.UP;
                    model.up();
                    break;
                case KeyEvent.VK_DOWN:
                    view.arrow = Arrow.DOWN;
                    model.down();
                    break;
                case KeyEvent.VK_Z:
                    model.rollback();
                    break;
                case KeyEvent.VK_R:
                    model.randomMove();
                    break;
                case KeyEvent.VK_A:
                    model.autoMove();
                    break;
                case KeyEvent.VK_Q:
                    if (model.isQuickMove) {
                        model.isQuickMove = false;
                        view.quick = null;
                    }
                    else {
                        model.isQuickMove = true;
                        model.quickMove(view);
                    }
                    break;
            }
            if (model.maxTile == WINNING_TILE) view.isGameWon = true;
        }
        view.repaint();
    }

    public void resetGame() {
        model.score = 0;
        view.isGameWon = false;
        view.isGameLost = false;
        model.resetGameTiles();
    }

    public Tile[][] getGameTiles() {
        return model.getGameTiles();
    }

    public int getScore() {
        return model.score;
    }

    public View getView() {
        return view;
    }
}
