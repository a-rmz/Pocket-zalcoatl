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
        this(context);
        holder = getHolder();
        this.animations = animations;
    }

    public DrawSurface(Context context) {
        super(context);
    }


    @Override
    public void run() {
        for(Animation a : animations) {
            a.restart();
        }
        while(isRunning) {
            Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
            p.setColor(Color.BLACK);
            // Canvas draw
            if(holder.getSurface().isValid()) {
                canvas = holder.lockCanvas();
                // Draws the animations
                for(Animation a : animations) {
                    canvas.drawBitmap(a.getCurrentImage(), 0, 0, p);
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

    public void pauseAnimations() {
        try {
            for(Animation a : animations) {
                a.wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resumeAnimations() {
        try {
            for(Animation a : animations) {
                a.notify();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
