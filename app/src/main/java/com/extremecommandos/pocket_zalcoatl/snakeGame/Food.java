package com.extremecommandos.pocket_zalcoatl.snakeGame;

/**
 * Created by kevin on 24/04/2016.
 */
public class Food {

    private int x;
    private int y;
    private int size;
    private int x2;
    private int y2;

    public Food(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.x2 = x + size;
        this.y2 = y + size;
        this.size = size;
    }

    public int getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public void setX(int x) {
        this.x = x;
        this.x2 = x + size;
    }

    public void setY(int y) {

        this.y = y;
        this.y2 = y+ size;
    }
}
