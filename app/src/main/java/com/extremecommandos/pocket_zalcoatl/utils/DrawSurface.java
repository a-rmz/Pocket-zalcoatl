package com.extremecommandos.pocket_zalcoatl.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by alex on 3/10/16.
 */
public class DrawSurface extends SurfaceView implements Runnable{

    Thread draw;
    SurfaceHolder holder;
    Bitmap bmp;
    Bitmap blank = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
    Canvas canvas;
    boolean isRunning = false;

    public DrawSurface(Context context, SurfaceHolder surfaceHolder) {
        super(context);
        holder = surfaceHolder;
    }

    protected void updateImage(Bitmap bmp) {
        this.bmp = bmp;
    }

    protected Bitmap getBmp() {
        return bmp;
    }

    @Override
    public void run() {
        while(isRunning) {
            // Canvas draw
            if(!holder.getSurface().isValid()) {
                continue;
            }
            canvas = holder.lockCanvas();
            canvas.drawBitmap(getBmp(), 0, 0, null);
            holder.unlockCanvasAndPost(canvas);
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