package com.extremecommandos.pocket_zalcoatl.flappySnake.characters;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.extremecommandos.pocket_zalcoatl.R;
import com.extremecommandos.pocket_zalcoatl.flappySnake.utils.Dimensions;
import com.extremecommandos.pocket_zalcoatl.flappySnake.utils.Position;

/**
 * Created by alex on 4/8/16.
 */
public class Snake {

    protected Position pos;
    private Dimensions dimensions;
    public Bitmap image;

    public Snake(Dimensions dimensions, Resources resources) {
        this.dimensions = dimensions;
        image = BitmapFactory.decodeResource(resources, R.drawable.quetz);
        init();
    }

    private void init() {
        pos = new Position(
                dimensions.getWidthPx() / 2,
                dimensions.getHeightPx() / 2,
                0,
                30,
                dimensions.getHeightPx(),
                dimensions.dpToPx(4));
        pos.setDimens(image);
    }

    public void update() {
        pos.movePosY();
    }

    public Position getPos() {
        return this.pos;
    }

    public boolean isAlive() {
        return pos.getPosY() < (pos.getMaxY() + 2 * pos.getHeight());

    }

    public void onTap(MotionEvent event) {
        //int dy = -(int) ((pressure > 0.19) ? (270 * pressure) : (220 * pressure));
        if(isAlive()) {
            int dy = -30;
            pos.setDy(dimensions.dpToPx(dy));
        }
    }

    public Rect getCollider() {
        return new Rect(pos.getPosX() - pos.getWidth() / 2, pos.getPosY(), pos.getPosX() + pos.getWidth() / 2, pos.getPosY() + pos.getHeight());
    }

}
