package com.extremecommandos.pocket_zalcoatl.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by alex on 3/10/16.
 */
public class DrawSurface extends SurfaceView implements Runnable{

    Thread draw;
    SurfaceHolder holder;
    Animation [] animations;
    Canvas canvas;
    boolean isRunning = false;

    public DrawSurface(Context context, Animation [] animations) {
        super(context);
        holder = getHolder();
        this.animations = animations;
    }


    @Override
    public void run() {
        while(isRunning) {
            // Canvas draw
            if(holder.getSurface().isValid()) {
                canvas = holder.lockCanvas();
                // Cleans the frame
                canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                // Draws the animations
                for(Animation a : animations) {
                    canvas.drawBitmap(a.getCurrentImage(), 0, 0, null);
                }
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }

    public void pause() {
        isRunning = false;
        while (true) {
            try {
                draw.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            break;
        }
        draw = null;
    }

    public void resume() {
        isRunning = true;
        draw = new Thread(this);
        draw.start();
    }


}
