package com.extremecommandos.pocket_zalcoatl.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by alex on 3/10/16.
 */
public class DrawSurface extends SurfaceView implements Runnable{

    Thread draw;
    SurfaceHolder holder;
    boolean isRunning = false;

    public DrawSurface(Context context, SurfaceHolder surfaceHolder) {
        super(context);
        holder = surfaceHolder;
        resume();

    }

    public void update() {

    }

    @Override
    public void run() {
        while(isRunning) {
            // Canvas draw
            if(!holder.getSurface().isValid()) {
                continue;
            }
            Canvas canvas = holder.lockCanvas();
            canvas.drawARGB(155, 120, 200, 30);
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
