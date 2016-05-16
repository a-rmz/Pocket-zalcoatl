package com.extremecommandos.pocket_zalcoatl.flappySnake.background;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.extremecommandos.pocket_zalcoatl.R;
import com.extremecommandos.pocket_zalcoatl.flappySnake.utils.Dimensions;
import com.extremecommandos.pocket_zalcoatl.flappySnake.utils.Position;

/**
 * Created by alex on 4/11/16.
 */
public class Background {

    Dimensions dimensions;
    Position position;
    Bitmap bg;
    Rect rect;

    public Background(Dimensions dimensions, Resources resources) {
        position = new Position();
        this.dimensions = dimensions;
        bg = BitmapFactory.decodeResource(resources, R.drawable.background_sky);
        position.setDimens(dimensions.getWidthPx(), dimensions.getHeightPx());
        rect = new Rect(0, 0, position.getWidth(), position.getHeight());
    }

    public Bitmap getBg() {
        return bg;
    }

    public Rect getViewBounds() {
        return this.rect;
    }

    public Position getPosition() {
        return position;
    }
}
