package com.extremecommandos.pocket_zalcoatl.flappySnake.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.SurfaceHolder;

import com.extremecommandos.pocket_zalcoatl.flappySnake.blocks.Column;
import com.extremecommandos.pocket_zalcoatl.flappySnake.blocks.ColumnManager;
import com.extremecommandos.pocket_zalcoatl.flappySnake.characters.Snake;

/**
 * Created by alex on 4/8/16.
 */
public class GameLoop implements Runnable {

    int fps;
    boolean running;
    private Game game;
    private Snake snake;
    private ColumnManager columnManager;
    private SurfaceHolder surfaceHolder;
    Paint p, t, shadow, text;

    public GameLoop(Game game, SurfaceHolder surfaceHolder) {
        fps = 60;
        running = false;
        this.game = game;
        this.snake = game.snake;
        this.surfaceHolder = surfaceHolder;
        this.columnManager = game.columnManager;

        p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.RED);
        p.setStyle(Paint.Style.STROKE);

        t = new Paint(Paint.ANTI_ALIAS_FLAG);
        t.setColor(Color.BLACK);
        t.setStyle(Paint.Style.FILL);

        shadow = new Paint(Paint.ANTI_ALIAS_FLAG);
        shadow.setColor(Color.BLACK);
        shadow.setStyle(Paint.Style.FILL);
        shadow.setAlpha(195);

        text = new Paint(Paint.ANTI_ALIAS_FLAG);
        text.setColor(Color.WHITE);
        text.setStyle(Paint.Style.FILL);
        text.setTextSize(50);
        text.setTypeface(Typeface.createFromAsset(game.resources.getAssets(), "fonts/Tribeca.ttf"));
    }

    @Override
    public void run() {
        running = true;
        int LoopTime = 1000 / fps; // 60 FPS
        long start, elapsed, wait;

        // Initializes what is needed for the Game.

        start = System.nanoTime();
        elapsed = System.nanoTime() - start;
        wait = LoopTime - elapsed / 1000000;

        if (wait < 0) wait = 5;

        while (running) {
            update();
            draw();

            if (wait < 0) wait = 5;
            try {
                Thread.sleep(wait);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        running = false;
    }

    private void update() {
        if(game.hasStarted() && !hasLost()) {
            snake.update();
            game.columnManager.update();
            for(Column c : game.columnManager.columns) {
                if(
                    c.getCollider().right <= snake.getCollider().left &&
                    c.getCollider().right >= snake.getCollider().left - 10
                    ) game.pointCounter.plusOne();
            }
        }

    }

    private void draw() {
        synchronized (surfaceHolder) {
            Canvas canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                try {
                    updateCanvas(canvas);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    private void updateCanvas(Canvas canvas) {

        // Background 1
        canvas.drawBitmap(game.background.getBg(), game.background.getViewBounds(), game.getScreenDimens(), t);

        if(game.hasStarted() && !hasLost()) {
            // Snake collider
            canvas.drawRect(snake.getCollider(), p);
            // Snake bmp
            canvas.drawBitmap(snake.image, snake.getPos().getPosX() - snake.getPos().getWidth() / 2, snake.getPos().getPosY(), t);
            // Shadows
            for(Column c : columnManager.columns) canvas.drawBitmap(columnManager.shadow, c.getPos().getPosX(), c.getPos().getPosY(), t);
            // Columns
            for(Column c : columnManager.columns) canvas.drawBitmap(c.image, c.getPos().getPosX(), c.getPos().getPosY(), t);
            // Score & points
            canvas.drawBitmap(game.pointCounter.getScore(),
                    game.getScreenDimens().right - game.pointCounter.getScore().getWidth() - game.dimensions.dpToPx(10),
                    game.dimensions.dpToPx(15),
                    t);
            canvas.drawText(game.pointCounter.getPoints(),
                    game.getScreenDimens().right - game.pointCounter.getScore().getWidth()/2 - game.dimensions.dpToPx(45),
                    game.dimensions.dpToPx(45),
                    text);
            // Back arrow
            canvas.drawBitmap(
                    game.goBack,
                    game.getBackArrow().left,
                    game.getBackArrow().top,
                    t);

            canvas.drawRect(game.getBackArrow(), p);
        } else if(hasLost()) {
            canvas.drawRect(game.lost.getDialogFrame(), p);
            canvas.drawText("Woah, you earned",
                    game.lost.getDialogFrame().left + game.dimensions.dpToPx(5),
                    game.lost.getDialogFrame().top + game.dimensions.dpToPx(25),
                    text);
            canvas.drawRect(game.lost.getBtn(), p);
        } else {
            canvas.drawPaint(shadow);
            canvas.drawBitmap(game.start,
                    game.getScreenDimens().centerX() - game.start.getWidth()/2,
                    game.getScreenDimens().centerY() - game.start.getHeight()/2,
                    t);
        }

    }

    public boolean hasLost() {

        for(Column c : columnManager.columns) {
            if (c.getCollider().intersect(snake.getCollider())) {
                return true;
            }
        }
        return !snake.isAlive();
    }

}
