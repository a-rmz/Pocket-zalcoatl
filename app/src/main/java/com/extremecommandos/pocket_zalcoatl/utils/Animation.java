package com.extremecommandos.pocket_zalcoatl.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.SurfaceHolder;

import com.extremecommandos.pocket_zalcoatl.MainActivity;
import com.extremecommandos.pocket_zalcoatl.R;

/**
 * Created by alex on 3/10/16.
 */
public class Animation implements Runnable {

    Thread animation;

    MainActivity activity;
    int index;
    DrawSurface drawSurface;
    boolean animationRunning;

    // Replace
    Bitmap [] spriteSheet;
    SpriteSheetLoader ssl;

    public Animation(MainActivity activity, SurfaceHolder surfaceHolder, Bitmap bmp) {
        this.activity = activity;
        index = 0;

        drawSurface = new DrawSurface(activity.getApplicationContext(), surfaceHolder);

        ssl = new SpriteSheetLoader(293, 245, 4, 1, bmp);
        spriteSheet = ssl.getSpriteSheet();
    }

    public void update() {
        if(++index > spriteSheet.length-1) index = 0;
        drawSurface.updateImage(spriteSheet[index]);

    }

    @Override
    public void run() {
        drawSurface.resume();
        int LoopTime = 1000 / 10; // 60 FPS
        long start, elapsed, wait;

        // Initializes what is needed for the Game.

        start= System.nanoTime();
        elapsed = System.nanoTime() - start;
        wait = LoopTime - elapsed / 1000000;

        if(wait < 0) wait = 5;

        while(animationRunning){

            update();

            if(wait < 0) wait = 5;

            try{
                animation.sleep(wait);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void pause() {
        drawSurface.pause();
        animationRunning = false;
        while (true) {
            try {
                animation.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            break;
        }
        animation = null;
    }

    public void stop() {
        pause();
    }

    public void resume() {
        animationRunning = true;
        animation = new Thread(this);
        animation.start();
    }

    public void restart() {
        drawSurface.resume();
        resume();
    }

}
