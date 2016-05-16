package com.extremecommandos.pocket_zalcoatl.snakeGame;

/**
 * Created by kevin on 13/04/2016.
 */
public class Snake{

    private float x=0;
    private float y=0;
    private float x2=0;
    private float y2=0;
    private float vel;
    private int size;


    public Snake(int distance){
        this.size=distance;
        x = 0f;
        y = 0f;
        x2=x+distance;
        y2=y+distance;
       vel = 10f;

    }

    public void Update(int i) {

        switch (i) {
                case 1: x += vel;
                        x2 +=vel;
                    break;
                case 2: x -= vel;
                        x2-= vel;
                    break;
                case 3: y += vel;
                        y2 += vel;
                    break;
                case 4: y -= vel;
                        y2 -= vel;
                    break;
        }

    }
    public float getX(){
        return  x;
    }

    public float getY(){
        return  y;
    }

    public float getX2() {
        return x2;
    }

    public float getY2() {
        return y2;
    }

    public void setX(float x){
        this.x = x;
        this.x2 = x+size;
    }

    public void setY(float y){

        this.y = y;
        this.y2 = y+size;
    }


    public void setVelocity( float newVel){
        vel = newVel;
    }


}
