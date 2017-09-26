package com.javarush.task.task35.task3513;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel {
    private static final Color BG_COLOR = new Color(0xbbada0);
    private static final String FONT_NAME = "Arial";
    private static final int TILE_SIZE = 96;
    private static final int TILE_MARGIN = 12;

    Arrow arrow;
    Color quick;

    private Controller controller;

    boolean isGameWon = false;
    boolean isGameLost = false;

    public View(Controller controller) {
        setFocusable(true);
        this.controller = controller;
        addKeyListener(controller);
    }

    private void side(Graphics g) {
        int xHelp = 500;
        int yHelp = 300;
        g.fillRoundRect(xHelp - 20, yHelp, 210, 50, 8, 8);
        g.setColor(new Color(0xe0e0e0));
        g.drawString("Your action:", xHelp - 20, yHelp + 35);
        if (arrow == Arrow.LEFT) {
            g.drawLine(xHelp + 150, yHelp + 20, xHelp + 170, yHelp + 20); //--
            g.drawLine(xHelp + 150, yHelp + 30, xHelp + 170, yHelp + 30); //__
            g.drawLine(xHelp + 170, yHelp + 20, xHelp + 170, yHelp + 30);// |

            g.drawLine(xHelp + 150, yHelp + 20, xHelp + 150, yHelp + 15);
            g.drawLine(xHelp + 150, yHelp + 15, xHelp + 140, yHelp + 25);

            g.drawLine(xHelp + 150, yHelp + 30, xHelp + 150, yHelp + 35);
            g.drawLine(xHelp + 150, yHelp + 35, xHelp + 140, yHelp + 25);
        }
        if (arrow == Arrow.RIGHT) {
            g.drawLine(xHelp + 150, yHelp + 20, xHelp + 170, yHelp + 20);
            g.drawLine(xHelp + 150, yHelp + 30, xHelp + 170, yHelp + 30);
            g.drawLine(xHelp + 150, yHelp + 20, xHelp + 150, yHelp + 30);

            g.drawLine(xHelp + 170, yHelp + 20, xHelp + 170, yHelp + 15);
            g.drawLine(xHelp + 170, yHelp + 15, xHelp + 180, yHelp + 25);

            g.drawLine(xHelp + 170, yHelp + 30, xHelp + 170, yHelp + 35);
            g.drawLine(xHelp + 170, yHelp + 35, xHelp + 180, yHelp + 25);
        }
        if (arrow == Arrow.UP) {
            g.drawLine(xHelp + 155, yHelp + 20, xHelp + 155, yHelp + 40);
            g.drawLine(xHelp + 165, yHelp + 20, xHelp + 165, yHelp + 40);
            g.drawLine(xHelp + 155, yHelp + 40, xHelp + 165, yHelp + 40);

            g.drawLine(xHelp + 155, yHelp + 20, xHelp + 150, yHelp + 20);
            g.drawLine(xHelp + 150, yHelp + 20, xHelp + 160, yHelp + 10);

            g.drawLine(xHelp + 165, yHelp + 20, xHelp + 170, yHelp + 20);
            g.drawLine(xHelp + 170, yHelp + 20, xHelp + 160, yHelp + 10);

        }
        if (arrow == Arrow.DOWN) {
            g.drawLine(xHelp + 155, yHelp + 10, xHelp + 155, yHelp + 30);
            g.drawLine(xHelp + 165, yHelp + 10, xHelp + 165, yHelp + 30);
            g.drawLine(xHelp + 155, yHelp + 10, xHelp + 165, yHelp + 10);

            g.drawLine(xHelp + 155, yHelp + 30, xHelp + 150, yHelp + 30);
            g.drawLine(xHelp + 150, yHelp + 30, xHelp + 160, yHelp + 40);

            g.drawLine(xHelp + 165, yHelp + 30, xHelp + 170, yHelp + 30);
            g.drawLine(xHelp + 170, yHelp + 30, xHelp + 160, yHelp + 40);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(BG_COLOR);
        g.fillRect(0, 0, this.getSize().width, this.getSize().height);
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                drawTile(g, controller.getGameTiles()[y][x], x, y);

            }
        }
        g.drawString("Score: " + controller.getScore(), 140, 465);
        // - my costume edit begin
        g.setColor(new Color(0xe0e0e0));
        g.fillRoundRect(480, 20, 210, 250, 80, 8);
        g.setFont(new Font(FONT_NAME, Font.ITALIC, 26));
        g.setColor(quick != null ? quick : new Color(0x000000));
        g.drawString("q - quick move", 490, 120);
        g.setColor(new Color(0x000000));
        g.drawString("Instruction:", 520, 50);
        g.drawString("a - auto move", 490, 85);
//        g.drawString("q - quick move", 490, 120);
        g.drawString("r - random move", 490, 155);
        g.drawString("z - undo move", 490, 190);
        g.drawString("esc - restart", 490, 235);
        side(g);
        // - end
        if (isGameWon) {
            JOptionPane.showMessageDialog(this, "You've won!");
        } else if (isGameLost) {
            JOptionPane.showMessageDialog(this, "You've lost :(");
        }
    }

    private void drawTile(Graphics g2, Tile tile, int x, int y) {
        Graphics2D g = ((Graphics2D) g2);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int value = tile.value;
        int xOffset = offsetCoors(x);
        int yOffset = offsetCoors(y);
        g.setColor(tile.getTileColor());
        g.fillRoundRect(xOffset, yOffset, TILE_SIZE, TILE_SIZE, 80, 80);
        g.setColor(tile.getFontColor());
        final int size = value < 100 ? 36 : value < 1000 ? 32 : 24;
        final Font font = new Font(FONT_NAME, Font.BOLD, size);
        g.setFont(font);

        String s = String.valueOf(value);
        final FontMetrics fm = getFontMetrics(font);

        final int w = fm.stringWidth(s);
        final int h = -(int) fm.getLineMetrics(s, g).getBaselineOffsets()[2];

        if (value != 0)
            g.drawString(s, xOffset + (TILE_SIZE - w) / 2, yOffset + TILE_SIZE - (TILE_SIZE - h) / 2 - 2);
    }

    private static int offsetCoors(int arg) {
        return arg * (TILE_MARGIN + TILE_SIZE) + TILE_MARGIN;
    }
}
