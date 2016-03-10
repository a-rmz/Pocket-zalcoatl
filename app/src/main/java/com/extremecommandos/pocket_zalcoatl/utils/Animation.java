package com.extremecommandos.pocket_zalcoatl.utils;

import android.graphics.Canvas;

import com.extremecommandos.pocket_zalcoatl.MainActivity;

/**
 * Created by alex on 3/10/16.
 */
public class Animation implements Runnable {

    MainActivity activity;
    int spriteCount, index;
    Canvas canvas;
    boolean animationRunning;

    public Animation(MainActivity activity, int spriteCount) {
        this.activity = activity;
        this.spriteCount = spriteCount;
        index = 0;
    }

    public void update() {

    }

    public void draw() {

    }

    @Override
    public void run() {

    }

    public void pause() {
        animationRunning = false;
    }

    public void resume() {
        animationRunning = true;
    }


}
