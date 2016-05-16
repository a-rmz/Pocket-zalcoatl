package com.extremecommandos.pocket_zalcoatl.flappySnake.game;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.extremecommandos.pocket_zalcoatl.MainActivity;
import com.extremecommandos.pocket_zalcoatl.flappySnake.FlappySnakeMain;
import com.extremecommandos.pocket_zalcoatl.flappySnake.utils.Dimensions;

/**
 * Created by alex on 4/8/16.
 */
public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {

    Game game;
    FlappySnakeMain main;
    Context context;
    boolean started;

    public GameSurface(FlappySnakeMain main, Resources resources) {
        super(main.getApplicationContext());
        this.main = main;
        this.context = main.getApplicationContext();
        started = false;
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        int density = getResources().getConfiguration().densityDpi;
        int width = getResources().getConfiguration().screenWidthDp;
        int height = getResources().getConfiguration().screenHeightDp;
        game = new Game(holder, getResources(), new Dimensions(width, height, density), this);
        game.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (game != null) {
            game.stop();
        }
        game = null;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        game.onTap(event);
        return true;
    }

    public void onPause() {
        if(game != null) {
            game.onPause();
        }
    }

    public void onResume() {
        if(game != null) {
            game.onResume();
        }
    }

    public void returnToMainActivity(int score) {
        System.out.println("Return 2 main GameSurface");
        main.returnToMainActivity(score);
    }
}
