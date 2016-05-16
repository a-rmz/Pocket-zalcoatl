package com.extremecommandos.pocket_zalcoatl.snakeGame;



import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.view.SurfaceHolder;

/**
 * Created by kevin on 18/04/2016.
 */
public class GameSnake {
    private final Thread thread;
    private  GameLoopSnake gameLoop;

    public  GameSnake(SurfaceHolder surfaceHolder, Resources resources, Point size, DrawingActivitySnake drawingActivitySnake, Context context){
        gameLoop = new GameLoopSnake(surfaceHolder,resources, size, drawingActivitySnake, context);
        thread = new Thread(gameLoop);
    }

    public void start(){
        thread.start();
    }

    public  void stop(){
        gameLoop.stop();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateOnTouch( int change){
        gameLoop.doPhysics(change);
    }
}
