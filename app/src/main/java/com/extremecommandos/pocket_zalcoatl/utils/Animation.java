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
    int index;
    DrawSurface drawSurface;
    boolean animationRunning;

    // Replace
    Bitmap bmp;
    Bitmap [] spriteSheet;
    SpriteSheetLoader ssl;

    public Animation(MainActivity activity) {
        this.activity = activity;
        index = 0;

        bmp = BitmapFactory.decodeResource(activity.getResources(), R.drawable.chibi_quetzal);
        drawSurface = new DrawSurface(activity.getApplicationContext(), activity.getCharacterSurfaceHolder());

        ssl = new SpriteSheetLoader(120, 120, 4, 4, bmp);
        spriteSheet = ssl.getSpriteSheet();

        resume();
    }

    public void update() {
        if(++index > spriteSheet.length) index = 0;
        drawSurface.updateImage(spriteSheet[index]);
    }

    @Override
    public void run() {
        drawSurface.updateImage(spriteSheet[1]);
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
