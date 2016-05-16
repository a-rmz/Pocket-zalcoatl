package com.extremecommandos.pocket_zalcoatl.flappySnake.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.extremecommandos.pocket_zalcoatl.R;

/**
 * Created by alex on 5/15/16.
 */
public class PointCounter {

    private Bitmap score;
    private int points;

    public PointCounter(Resources resources) {
        this.score = BitmapFactory.decodeResource(resources, R.drawable.score);
        this.points = 0;
    }

    public Bitmap getScore() {
        return this.score;
    }

    public String getPoints() {
        return String.valueOf(points);
    }

    public int getPointsInt() {
        return this.points;
    }

    public void plusOne() {
        this.points++;
    }
}
