package com.extremecommandos.pocket_zalcoatl.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.extremecommandos.pocket_zalcoatl.MainActivity;
import com.extremecommandos.pocket_zalcoatl.R;

/**
 * Created by alex on 3/10/16.
 */
public class Animation implements Runnable {

    Thread animation;

    MainActivity activity;
    int spriteCount, index;
    DrawSurface drawSurface;
    boolean animationRunning;

    // Replace
    Bitmap bmp[] = new Bitmap[3];

    public Animation(MainActivity activity, int spriteCount) {
        this.activity = activity;
        this.spriteCount = spriteCount;
        index = 0;

        bmp[0] = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_games_white_36dp);
        bmp[1] = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_favorite_white_36dp);
        bmp[2] = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_hotel_white_36dp);
        drawSurface = new DrawSurface(activity.getApplicationContext(), activity.getCharacterSurfaceHolder());

        resume();
    }

    public void update() {
        if(index == 0) index = 1;
        else if(index == 1) index = 2;
        else if(index == 2) index = 0;
        drawSurface.updateImage(bmp[index]);
    }

    @Override
    public void run() {
        drawSurface.updateImage(bmp[1]);
        drawSurface.run();
        while(animationRunning) {
            update();
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

    public void resume() {
        animationRunning = true;
        animation = new Thread(this);
        animation.start();
    }


}
