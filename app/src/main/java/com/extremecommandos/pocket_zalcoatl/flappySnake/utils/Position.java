package com.extremecommandos.pocket_zalcoatl.flappySnake.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.util.Random;

/**
 * Created by alex on 4/8/16.
 */
public class Position {

    private Random rand;
    private int posX;
    private int posY;
    private int dx;
    private int dy;
    private int maxY;
    private int maxX;
    private int gravity;
    private int width;
    private int height;

    public Position(int posX, int posY, int dx, int dy, int maxY, int gravity) {
        this.posX = posX;
        this.posY = posY;
        this.dx = dx;
        this.dy = dy;
        this.maxY = maxY;
        this.gravity = gravity;
        rand = new Random();
        rand.setSeed(System.currentTimeMillis());
    }

    public Position(int posX, int posY, int dx, int dy, int maxX, int maxY, int gravity) {
        this.posX = posX;
        this.posY = posY;
        this.dx = dx;
        this.dy = dy;
        this.maxY = maxY;
        this.maxX = maxX;
        this.gravity = gravity;
        rand = new Random();
        rand.setSeed(System.currentTimeMillis());
    }


    public Position() {
        this.posX = 0;
        this.posY = 0;
        this.dx = 0;
        this.dy = 0;
        this.maxY = 0;
        this.maxX = 0;
        rand = new Random();
        rand.setSeed(System.currentTimeMillis());
    }

    public int getDy() {
        return dy;
    }

    public int getDx() {
        return dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getPosY() {
        return posY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosY(int posY) {
        if (posY >= 0) this.posY = posY;
        else {
            this.posY = 0;
            setDy(gravity);
        }
    }

    public void moveColumnY(int posY) {
        this.posY = posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getMaxY() {
        return this.maxY;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public int getMaxX() {
        return maxX;
    }

    public void movePosY() {
        dy = /*(dy <= 0) ? dy + 3 :*/ dy + gravity;
        setPosY(posY + dy);
    }

    public void movePosX(int param, int dir) {
        if (this.posX < param) {
            setPosX(posX + dx);
        } else {
            setPosX(dir * param);
        }
    }

    public void movePosX(int param, int dir, boolean inv) {
        setPosX(posX + dx);
    }

    public boolean isOnScreen() {
        if (this.posX > -(2*width) ) {
            return true;
        } else {
            return false;
        }
    }

    public void movePosXCounter() {
        if (this.posX > (maxX / 2) - gravity) {
            setPosX(posX + dx);
        } else {
            setPosX(posX + maxX - 1);
        }
    }

    public void setDimens(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setDimens(Drawable drawable) {
        width = drawable.getBounds().width();
        height = drawable.getBounds().height();
    }

    public void setDimens(Bitmap bitmap) {
        width = bitmap.getWidth();
        height = bitmap.getHeight();
    }


    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
