package com.extremecommandos.pocket_zalcoatl.SnakeGame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;


public class MySurfaceViewSnake extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener{


    private final Resources resources;
    private GameSnake game;
    private float initialX =0;
    private float initialy =0;
    private float distanceX =0;
    private float distanceY =0;
    private float absX=0;
    private float absy=0;
    private final DrawingActivitySnake drawingActivitySnake;
    private final Context context;




    public MySurfaceViewSnake(Context context, Resources resources, DrawingActivitySnake drawingActivitySnake) {
        super(context);
        this.context = context;
        this.resources = resources;
        game = null;
        setOnTouchListener(this);
        getHolder().addCallback(this);
        this.drawingActivitySnake = drawingActivitySnake;
    }



    public  void surfaceCreated(SurfaceHolder surfaceHolder){
        Point size = new Point();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getSize(size);

        game = new GameSnake(surfaceHolder, resources, size, drawingActivitySnake, context );
        game.start();

    }


    public  void  surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3){

    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder){

        if(game != null){
            game.stop();
        }
        game = null;
    }




    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                initialX = event.getX();
                initialy = event.getY();
                return true;
            case MotionEvent.ACTION_UP:
                distanceX = initialX - event.getX();
                distanceY = initialy - event.getY();

                absX=Math.abs(distanceX);
                absy = Math.abs(distanceY);

                if(absX >= absy){
                    if(distanceX>0)
                        game.updateOnTouch(2);
                    else
                        game.updateOnTouch(1);
                }else{
                    if(distanceY>0)
                        game.updateOnTouch(4);
                    else
                        game.updateOnTouch(3);
                }
                default:
                break;
        }
        return false;
    }
}
