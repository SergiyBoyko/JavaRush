package com.javarush.task.task35.task3513;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Serhii Boiko on 15.07.2017.
 */
public class Model {
    private static final int FIELD_WIDTH = 4;
    private boolean isSaveNeeded = true;
    private Tile[][] gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
    int score;
    int maxTile;
    boolean isQuickMove = false;

    private Stack previousStates = new Stack();
    private Stack previousScores = new Stack();

    private void saveState(Tile[][] tiles) {
        Tile[][] tempGameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                tempGameTiles[i][j] = new Tile(tiles[i][j].value);
            }
        }
        previousStates.push(tempGameTiles);
        int tempScore = score;
        previousScores.push(tempScore);
        isSaveNeeded = false;
    }

    public void rollback() {
        try {
            this.gameTiles = (Tile[][]) previousStates.pop();
            this.score = (int) previousScores.pop();
        } catch (EmptyStackException e) {
            System.out.println("Error: stack is empty!");
        }
    }

    public Model() {
        resetGameTiles();
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public boolean canMove() {
        Tile[][] tempGameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                tempGameTiles[i][j] = new Tile(gameTiles[i][j].value);
            }
        }
        boolean move = false;
        if (checkChange()) move = true;
        else {
            rotateClockwise();
            if (checkChange()) move = true;
            else {
                rotateClockwise();
                if (checkChange()) move = true;
                else {
                    rotateClockwise();
                    if (checkChange()) move = true;
                }
            }
        }
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j].value = tempGameTiles[i][j].value;
            }
        }
        return move;
    }

    public boolean hasBoardChanged() {
        Tile[][] tiles = (Tile[][]) previousStates.peek();
        int weight1 = 0;
        int weight2 = 0;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                weight1 += tiles[i][j].value;
                weight2 += gameTiles[i][j].value;
            }
        }
        return weight1 != weight2;
    }

    public MoveEfficiency getMoveEfficiency(Move move) {
        MoveEfficiency moveEfficiency;
        move.move();
        if (hasBoardChanged())
            moveEfficiency = new MoveEfficiency(this.getEmptyTiles().size(), score, move);
        else moveEfficiency = new MoveEfficiency(-1, 0, move);
        rollback();
        return moveEfficiency;
    }

    public void quickMove(View view) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isQuickMove) {
                    try {
                        autoMove();
                        view.repaint();
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println("Error: quick move");
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Color black = new Color(0x000000);
                Color red = new Color(0xf01040);
                while (isQuickMove) {
                    try {
                        view.quick = black;
                        Thread.sleep(500);
                        view.quick = red;
                        Thread.sleep(500);
//                        view.repaint();
                    } catch (InterruptedException e) {
                        System.out.println("Error: quick move");
                    }
                }
            }
        });
        thread2.setDaemon(true);
        thread2.start();
    }

    public void autoMove() {
//        System.out.println("auto move");
        PriorityQueue queue = new PriorityQueue(4, Collections.reverseOrder());
        queue.offer(getMoveEfficiency(this::up));
        queue.offer(getMoveEfficiency(this::right));
        queue.offer(getMoveEfficiency(this::down));
        queue.offer(getMoveEfficiency(this::left));
        MoveEfficiency efficiency = (MoveEfficiency) queue.peek();
        efficiency.getMove().move();
    }

    public void randomMove() {
        int n = ((int) (Math.random() * 100)) % 4;
        switch (n) {
            case 0:
                left();
                break;
            case 1:
                right();
                break;
            case 2:
                up();
                break;
            case 3:
                down();
                break;
        }
    }

    private boolean checkChange() {
        boolean isChange = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            boolean ct = compressTiles(gameTiles[i]);
            boolean mt = mergeTiles(gameTiles[i]);
            if (ct | mt) {
                isChange = true;
            }
        }
        return isChange;
    }

    public void resetGameTiles() {
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < gameTiles[i].length; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
        score = 0;
        maxTile = 2;
    }

    private void addTile() {
        List<Tile> emptyTiles = getEmptyTiles();
        if (!emptyTiles.isEmpty())
            emptyTiles.get((int) (emptyTiles.size() * Math.random())).value = Math.random() < 0.9 ? 2 : 4;
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> tile = new ArrayList<Tile>();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].isEmpty())
                    tile.add(gameTiles[i][j]);
            }
        }
        return tile;
    }

    private boolean compressTiles(Tile[] tiles) {
        boolean isCompressed = false;
        for (int i = 0; i < tiles.length - 1; i++) {
            for (int j = 0; j < tiles.length - 1; j++) {
                if (tiles[j].isEmpty() && !tiles[j + 1].isEmpty()) {
                    tiles[j] = tiles[j + 1];
                    tiles[j + 1] = new Tile();
                    isCompressed = true;
                }
            }
        }
        return isCompressed;
    }

    private boolean mergeTiles(Tile[] tiles) {  //Слияние плиток одного номинала
        boolean isMerges = false;
        for (int j = 0; j < tiles.length - 1; j++) {
            if (tiles[j].value == tiles[j + 1].value && !tiles[j].isEmpty() && !tiles[j + 1].isEmpty()) {
                tiles[j].value = tiles[j].value * 2;
                isMerges = true;
                if (Thread.currentThread().getStackTrace()[3].getMethodName().equals("left")) {
                    score += tiles[j].value;
                    maxTile = maxTile > tiles[j].value ? maxTile : tiles[j].value;
                }
                tiles[j + 1] = new Tile();
                compressTiles(tiles);
            }
        }
        return isMerges;
    }

    void left() {
        if (isSaveNeeded) {
            saveState(gameTiles);
//            System.out.println("left");
        }
        if (checkChange()) {
            addTile();
            isSaveNeeded = true;
        }
    }

    void right() {
//        System.out.println("right");
        saveState(gameTiles);
        rotateClockwise();
        rotateClockwise();
        left();
        rotateClockwise();
        rotateClockwise();
    }

    void up() {
//        System.out.println("up");
        saveState(gameTiles);
        rotateClockwise();
        left();
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
    }

    void down() {
//        System.out.println("down");
        saveState(gameTiles);
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
        left();
        rotateClockwise();
    }

    private void rotateClockwise() {
        for (int k = 0; k < FIELD_WIDTH / 2; k++) // border -> center
        {
            for (int j = k; j < FIELD_WIDTH - 1 - k; j++) // left -> right
            {
                // меняем местами 4 угла
                int tmp = gameTiles[k][j].value;
                gameTiles[k][j].value = gameTiles[j][FIELD_WIDTH - 1 - k].value;
                gameTiles[j][FIELD_WIDTH - 1 - k].value = gameTiles[FIELD_WIDTH - 1 - k][FIELD_WIDTH - 1 - j].value;
                gameTiles[FIELD_WIDTH - 1 - k][FIELD_WIDTH - 1 - j].value = gameTiles[FIELD_WIDTH - 1 - j][k].value;
                gameTiles[FIELD_WIDTH - 1 - j][k].value = tmp;
            }
        }
    }

//    public static void main(String[] args) {
//        Model model = new Model();
//        int x = 0;
//        for (int i = 0; i < model.gameTiles.length; i++) {
//            for (int j = 0; j < model.gameTiles[i].length; j++) {
//                model.gameTiles[i][j].value = x++;
//            }
//        }
//
//        for (int i = 0; i < model.gameTiles.length; i++) {
//            for (int j = 0; j < model.gameTiles[i].length; j++) {
//                System.out.print(model.gameTiles[i][j].value + " ");
//            }
//            System.out.println();
//        }
//        System.out.println(model.canMove());
//        model.up();
//        for (int i = 0; i < model.gameTiles.length; i++) {
//            for (int j = 0; j < model.gameTiles[i].length; j++) {
//                System.out.print(model.gameTiles[i][j].value + " ");
//            }
//            System.out.println();
//        }
//
//        model.left();
//        System.out.println(model.canMove());
//        for (int i = 0; i < model.gameTiles.length; i++) {
//            for (int j = 0; j < model.gameTiles[i].length; j++) {
//                System.out.print(model.gameTiles[i][j].value + " ");
//            }
//            System.out.println();
//        }
//
//        model.up();
//        System.out.println();
//        for (int i = 0; i < model.gameTiles.length; i++) {
//            for (int j = 0; j < model.gameTiles[i].length; j++) {
//                System.out.print(model.gameTiles[i][j].value + " ");
//            }
//            System.out.println();
//        }
//
//        model.down();
//        System.out.println();
//        for (int i = 0; i < model.gameTiles.length; i++) {
//            for (int j = 0; j < model.gameTiles[i].length; j++) {
//                System.out.print(model.gameTiles[i][j].value + " ");
//            }
//            System.out.println();
//        }
//        model.left();
//        System.out.println();
//        for (int i = 0; i < model.gameTiles.length; i++) {
//            for (int j = 0; j < model.gameTiles[i].length; j++) {
//                System.out.print(model.gameTiles[i][j].value + " ");
//            }
//            System.out.println();
//        }
//        model.left();
//        System.out.println();
//        for (int i = 0; i < model.gameTiles.length; i++) {
//            for (int j = 0; j < model.gameTiles[i].length; j++) {
//                System.out.print(model.gameTiles[i][j].value + " ");
//            }
//            System.out.println();
//        }
//        Tile[] tiles = new Tile[4];
//        tiles[0] = new Tile(8);
//        tiles[1] = new Tile(2);
//        tiles[2] = new Tile(4);
//        tiles[3] = new Tile(2);
//
//        for (int i = 0; i < tiles.length; i++) {
//            System.out.print(tiles[i].value + " ");
//        }
//        System.out.println(compressTiles(tiles) + "\n");
////        compressTiles(tiles);
//        for (int i = 0; i < tiles.length; i++) {
//            System.out.print(tiles[i].value + " ");
//        }
//        System.out.println(mergeTiles(tiles) + "\n");
////        mergeTiles(tiles);
//        for (int i = 0; i < tiles.length; i++) {
//            System.out.print(tiles[i].value + " ");
//        }
//    }
}
