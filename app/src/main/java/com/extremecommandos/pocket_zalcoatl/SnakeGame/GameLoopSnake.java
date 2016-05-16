package com.extremecommandos.pocket_zalcoatl.SnakeGame;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.SurfaceHolder;

import com.extremecommandos.pocket_zalcoatl.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by kevin on 11/04/2016.
 */
public class GameLoopSnake  implements Runnable {

    private final SurfaceHolder surfaceHolder;
    private long start, elapsed, wait;
    private int FPS = 10;
    private long  LoopTime = 1000 / FPS;

    protected boolean running = true;
    private final  Snake snake;
    private final Paint paint;
    private final Context context;
    private final  DrawingActivitySnake drawingActivitySnake;


    private Bitmap snakeBitmap_right;
    private Bitmap scoreBitmap;
    private Bitmap background;
    private Bitmap background2;
    private Bitmap snakeBitmap_left;
    private Bitmap snakeBitmap_down;
    private Bitmap snakeBitmap_up;
    private Bitmap snakeBody;
    private Bitmap bitmapFood;

    private int score;
    private int snakeLarge=0;
    private int changeVel;
    private int changeOrientation;
    private final float viewHeight;
    private final float viewWidth;
    private int lastStage =1;
    private List<Snake> bodySnake;
    private float fontSize;
    private float lastX;
    private float lastY;
    private float tmpLastX;
    private float tmpLastY;

    private Food food;
    Random random;



    private Canvas canvas;

    public GameLoopSnake(SurfaceHolder surfaceHolder, Resources resources, Point size,DrawingActivitySnake drawingActivitySnake,  Context context){
        Log.i("STATE", "size papu " + size.x + " " + size.y);// see in console
        this.surfaceHolder = surfaceHolder;
        this.context = context;
        this.drawingActivitySnake = drawingActivitySnake;
        viewHeight = size.y;
        viewWidth = size.x;
        random = new Random();
        changeVel = 1;
        changeOrientation = 1;
        paint = new Paint();
        score =0;
        fontSize = viewWidth / 15;
        paint.setColor(Color.WHITE);
        paint.setTextSize(fontSize);


        background = BitmapFactory.decodeResource(resources, R.mipmap.background);
        background2 = BitmapFactory.decodeResource(resources, R.mipmap.background);
        scoreBitmap = BitmapFactory.decodeResource(resources, R.drawable.score);
        snakeBitmap_right = BitmapFactory.decodeResource(resources, R.drawable.head_right);
        snakeBitmap_left = BitmapFactory.decodeResource(resources, R.drawable.head_left);
        snakeBitmap_down = BitmapFactory.decodeResource(resources, R.drawable.head_down);
        snakeBitmap_up = BitmapFactory.decodeResource(resources, R.drawable.head);
        snakeBody = BitmapFactory.decodeResource(resources,R.drawable.body);
        bitmapFood = BitmapFactory.decodeResource(resources, R.drawable.heart);

        food = new Food(random.nextInt((int)viewWidth),random.nextInt((int)viewHeight),bitmapFood.getHeight());
        snake = new Snake(snakeBody.getHeight());
        snake.setVelocity(snakeBody.getHeight());

        bodySnake = new ArrayList<>();
        for (int i=0;i<snakeLarge;i++){
            bodySnake.add(new Snake(snakeBody.getHeight()));
        }

    }



    @Override
    public void run() {

        waitForNextFrame();

    }


    public void doPhysics(int change){

        switch (lastStage){
            case 1:
                if (change == 2) {
                    changeVel=changeOrientation= lastStage;
                    return;
                }
                else
                    changeVel=changeOrientation= change;
                break;
            case 2:
                if(change==1) {
                    changeVel=changeOrientation= lastStage;
                    return;
                }
                else
                    changeVel=changeOrientation= change;
                break;
            case 3:
                if(change==4) {
                    changeVel=changeOrientation= lastStage;
                    return;
                }
                else
                    changeVel=changeOrientation= change;
                break;
            case 4:
                if(change==3) {
                    changeVel=changeOrientation= lastStage;
                    return;
                }
                else
                    changeVel=changeOrientation= change;
                break;
            default: changeVel=changeOrientation= lastStage;
                break;
        }
        lastStage = change;


    }

    private void UpdateBody(){

        switch (lastStage){
            case 1:
                lastX = snake.getX()- snakeBody.getWidth();
                lastY = snake.getY();
                break;
            case 2:
                lastX = snake.getX()+ snakeBody.getWidth();
                lastY = snake.getY();
                break;
            case 3:
                lastX = snake.getX();
                lastY = snake.getY() -  snakeBody.getWidth();
                break;
            case 4:
                lastX = snake.getX();
                lastY = snake.getY() +  snakeBody.getWidth();
                break;
            default:
                break;
        }

        for(Snake snakebody: bodySnake){
            tmpLastX= snakebody.getX();
            tmpLastY= snakebody.getY();
            snakebody.setX(lastX);
            snakebody.setY(lastY);
            lastY=tmpLastY;
            lastX=tmpLastX;
        }
    }

    private void checkFood(){

        switch (lastStage){
            case 1:
                if( snake.getX2() >= food.getX() && ( snake.getY()>=(food.getY()-snakeBody.getHeight())
                   && snake.getY() <= (food.getY2())
                        )){
                  bodySnake.add(new Snake(snakeBody.getHeight()));
                    food.setX(random.nextInt((int) viewWidth) );
                    food.setY(random.nextInt((int) viewHeight) );
                    score++;
                }
                break;
            case 2:
                if( snake.getX()<= food.getX2() && ( snake.getY()>=(food.getY()-snakeBody.getHeight())
                        && snake.getY() <= (food.getY2())
                        )){
                    bodySnake.add(new Snake(snakeBody.getHeight()));
                    food.setX(random.nextInt((int) viewWidth)) ;
                    food.setY(random.nextInt((int) viewHeight));
                    score++;
                }
                break;
            case 3:
                if( snake.getY2() >=food.getY() && ( snake.getX()>= food.getX()-snakeBody.getWidth()
                        && snake.getX() <= food.getX2()
                        )){
                    bodySnake.add(new Snake(snakeBody.getHeight()));
                    food.setX(random.nextInt((int) viewWidth));
                    food.setY(random.nextInt((int) viewHeight));
                    score++;
                }
                break;
            case 4:
                if(snake.getY()<= food.getY2() &&( snake.getX()>= food.getX() -snakeBody.getWidth()
                        && snake.getX() <= food.getX2()
                         )){
                    bodySnake.add(new Snake(snakeBody.getHeight()));
                    food.setX(random.nextInt((int) viewWidth) );
                    food.setY(random.nextInt((int) viewHeight) );
                    score++;
                }
                break;
            default:
                break;
        }


    }

    private void checkLimits(){

        //Límite derecho
        if(snake.getX2() > viewWidth )
          snake.setX(0);
        //limite inferior
        else if(snake.getY2() > viewHeight)
           snake.setY(0);
        //limite izquierdo
        else if(snake.getX() < 0)
            snake.setX(viewWidth);
        //límite superior
        else if(snake.getY() < 0)
            snake.setY(viewHeight);
    }

    private void checkHitHimself(){

        for(Snake snakeParts: bodySnake){
            if(snake.getX() == snakeParts.getX() && snake.getY() == snakeParts.getY()) {
                stop();
                returnToMainActivity(score);
            }
        }

    }

    private void drawGraphics(){

        canvas = surfaceHolder.lockCanvas();


        if(canvas == null){
            return;
        }

        try{
            synchronized (surfaceHolder){
                actualDrawGraphics(canvas);
            }

        }
        finally {
            surfaceHolder.unlockCanvasAndPost(canvas);
        }

    }

    private void actualDrawGraphics(Canvas canvas) {

        canvas.drawColor(Color.BLACK);


            // draw background
        canvas.drawBitmap(
                background,
                0,
                0,
                paint
            );
        canvas.drawBitmap(
                background,
                0,
                background.getHeight(),
                paint
        );




        // draw head
        switch (changeOrientation) {
            case 1:
                canvas.drawBitmap(
                        snakeBitmap_right,
                        snake.getX() ,
                        snake.getY() ,
                        paint
                );
                break;
            case 2:
                canvas.drawBitmap(
                        snakeBitmap_left,
                        snake.getX() ,
                        snake.getY() ,
                        paint
                );
                break;
            case 3:
                canvas.drawBitmap(
                        snakeBitmap_down,
                        snake.getX() ,
                        snake.getY() ,
                        paint
                );
                break;
            case 4:
                canvas.drawBitmap(
                        snakeBitmap_up,
                        snake.getX() ,
                        snake.getY() ,
                        paint
                );
                break;
            default:
                canvas.drawBitmap(
                        snakeBitmap_right,
                        snake.getX() ,
                        snake.getY() ,
                        paint
                );
                break;
        }
        // draw body
        for(Snake snakebody: bodySnake){
            canvas.drawBitmap(
                    snakeBody,
                    snakebody.getX() ,
                    snakebody.getY() ,
                    paint
            );
        }
        // draw Score sprite
        canvas.drawBitmap(
                scoreBitmap,
                viewWidth - scoreBitmap.getWidth(),
                0,
                paint
        );

        canvas.drawText(String.valueOf(score),viewWidth-scoreBitmap.getWidth()+10 ,scoreBitmap.getHeight()-(scoreBitmap.getHeight()/4),paint);

        // draw food
        canvas.drawBitmap(
                bitmapFood,
                food.getX() ,
                food.getY() ,
                paint
        );
    }


    private void waitForNextFrame( ){

        start= System.nanoTime();
        elapsed = System.nanoTime() - start;
        wait = LoopTime - elapsed / 1000000;

        if (wait < 0) wait = 5;

        while (running){
            checkHitHimself();
            snake.Update(changeVel);
            UpdateBody();
            drawGraphics();
            checkFood();
            checkLimits();


            if(wait < 0) wait = 5;

            try{
                Thread.sleep(wait);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

    }

    public void stop(){
        running = false;
    }

    // Here we return the score to main activity
    public void returnToMainActivity(int score) {
        drawingActivitySnake.returnToMainActivity(score);
    }
}


