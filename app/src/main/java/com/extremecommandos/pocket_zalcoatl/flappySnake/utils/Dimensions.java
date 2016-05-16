package com.extremecommandos.pocket_zalcoatl.flappySnake.utils;

/**
 * Created by alex on 4/25/16.
 */
public class Dimensions {

    private int widthDp;
    private int heightDp;
    private int densityDp;
    private int widthPx;
    private int heightPx;


    public Dimensions(int widthDp, int heightDp, int densityDp) {
        this.widthDp = widthDp;
        this.heightDp = heightDp;
        this.densityDp = densityDp;
        this.widthPx = dpToPx(widthDp);
        this.heightPx = dpToPx(heightDp);
    }

    public int dpToPx(int dp) {
        return (dp * (densityDp / 160));
    }

    public int pxToDp(int px) {
        return ((px / densityDp) * 160);
    }

    public int getWidthDp() {
        return widthDp;
    }

    public void setWidthDp(int widthDp) {
        this.widthDp = widthDp;
    }

    public int getHeightDp() {
        return heightDp;
    }

    public void setHeightDp(int heightDp) {
        this.heightDp = heightDp;
    }

    public int getDensityDp() {
        return densityDp;
    }

    public void setDensityDp(int densityDp) {
        this.densityDp = densityDp;
    }

    public int getWidthPx() {
        return widthPx;
    }

    public void setWidthPx(int widthPx) {
        this.widthPx = widthPx;
    }

    public int getHeightPx() {
        return heightPx;
    }

    public void setHeightPx(int heightPx) {
        this.heightPx = heightPx;
    }


}
