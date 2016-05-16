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
import android.widget.ImageView;

import com.extremecommandos.pocket_zalcoatl.R;
import com.extremecommandos.pocket_zalcoatl.characters.PocketGod;

/**
 * Created by alex on 3/10/16.
 */
public class DrawSurface extends SurfaceView implements Runnable{

    Thread draw;
    SurfaceHolder holder;
    Animation [] animations;
    Canvas canvas;
    Paint night;

    boolean isRunning = false;

    public DrawSurface(Context context, Animation [] animations) {
        this(context);
        holder = getHolder();
        this.animations = animations;
        night = new Paint();
        night.setColor(getResources().getColor(R.color.night));
        night.setAlpha(210);
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
                canvas.drawBitmap(animations[0].getCurrentImage(), 0, 0, p);
                canvas.drawBitmap(
                        animations[1].getCurrentImage(),
                        canvas.getWidth() / 2 - animations[1].getCurrentImage().getWidth() / 2,
                        canvas.getHeight() / 2 - animations[1].getCurrentImage().getHeight() / 2,
                        p
                );
                if(PocketGod.isSleeping) {
                    canvas.drawPaint(night);
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
        draw = new Thread(this, "Draw");
        draw.start();
    }

    public void pauseAnimations() {
        synchronized (animations) {
            try {
                for(Animation a : animations) {
                    a.wait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void resumeAnimations() {
        synchronized (animations) {
            try {
                for(Animation a : animations) {
                    a.notify();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
