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

    int index, animationSpeed;
    boolean animationRunning;

    Bitmap [] spriteSheet;

    public Animation(Bitmap [] bmp, int animationSpeed) {
        this.animationSpeed = animationSpeed;
        spriteSheet = bmp;
        index = 0;
    }

    public void update() {
        index = (++index) % spriteSheet.length;
    }

    public Bitmap getCurrentImage() {
        return spriteSheet[index];
    }

    // Animation loop
    @Override
    public void run() {
        int LoopTime = 1000 / animationSpeed; // 60 FPS
        long start, elapsed, wait;

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
        resume();
    }

}
