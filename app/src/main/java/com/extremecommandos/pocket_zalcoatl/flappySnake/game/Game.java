package com.extremecommandos.pocket_zalcoatl.flappySnake.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.extremecommandos.pocket_zalcoatl.R;
import com.extremecommandos.pocket_zalcoatl.flappySnake.FlappySnakeMain;
import com.extremecommandos.pocket_zalcoatl.flappySnake.background.Background;
import com.extremecommandos.pocket_zalcoatl.flappySnake.blocks.ColumnManager;
import com.extremecommandos.pocket_zalcoatl.flappySnake.characters.Snake;
import com.extremecommandos.pocket_zalcoatl.flappySnake.utils.Dimensions;

/**
 * Created by alex on 4/8/16.
 */
public class Game {

    private Thread thread;
    private GameLoop gameLoop;
    private SurfaceHolder surfaceHolder;
    public Resources resources;
    public Snake snake;
    public Background background;
    public ColumnManager columnManager;
    public PointCounter pointCounter;
    public Dimensions dimensions;
    public LostDialog lost;
    private boolean hasStarted;
    public Bitmap start;
    public Bitmap goBack;
    private Rect back;
    private GameSurface parent;


    public Game(SurfaceHolder surfaceHolder, Resources resources, Dimensions dimensions, GameSurface parent) {
        this.surfaceHolder = surfaceHolder;
        this.resources = resources;
        this.parent = parent;

        this.dimensions = dimensions;

        snake = new Snake(dimensions, resources);
        background = new Background(dimensions, resources);
        columnManager = new ColumnManager(dimensions, resources);
        pointCounter = new PointCounter(resources);
        lost = new LostDialog(dimensions);

        this.start = BitmapFactory.decodeResource(resources, R.drawable.btn_start);
        this.goBack = BitmapFactory.decodeResource(resources, R.drawable.goback);
        back = new Rect(
                dimensions.dpToPx(10),
                dimensions.dpToPx(15),
                dimensions.dpToPx(10) + goBack.getWidth(),
                dimensions.dpToPx(15) + goBack.getHeight()
                );

        hasStarted = false;
        gameLoop = new GameLoop(this, surfaceHolder);
    }

    public void startGame() {
        hasStarted = true;
    }

    public void start() {
        thread = new Thread(gameLoop);
        thread.start();
    }

    public void stop() {
        gameLoop.stop();
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Gameloop: " + thread.isAlive());
    }

    public void endGame(int score) {
        System.out.println("Game ended");   
        returnToMainActivity(score);
    }

    public void onTap(MotionEvent event) {
        if(!hasStarted) {
            startGame();
        } else if (gameLoop.hasLost()){
            if(lost.getBtn().contains((int) event.getX(), (int) event.getY())) {
                endGame(pointCounter.getPointsInt());
            }
        } else {
            snake.onTap(event);
        }
        if(back.contains((int) event.getX(), (int) event.getY())) {
            endGame(0);
        }
    }

    public Rect getScreenDimens() {
        return new Rect(0, 0, dimensions.getWidthPx(), dimensions.getHeightPx() + 150);
    }

    public Rect getBackArrow() {
        return back;
    }

    public boolean hasStarted() {
        return hasStarted;
    }

    public void onPause() {
        synchronized (gameLoop) {
            try {
                gameLoop.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onResume() {
        synchronized (gameLoop) {
            try {
                gameLoop.notify();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void returnToMainActivity(int score) {
        System.out.println("Return 2 main Game");
        parent.returnToMainActivity(score);
    }
}
