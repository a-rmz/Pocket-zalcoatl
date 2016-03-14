package com.extremecommandos.pocket_zalcoatl.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Log;

import com.extremecommandos.pocket_zalcoatl.MainActivity;
import com.extremecommandos.pocket_zalcoatl.R;

/**
 * Created by alex on 3/10/16.
 */
public class SpriteSheetLoader {

    Bitmap source;

    int width, height, rows, columns;

    Bitmap[] spriteSheet;

    public SpriteSheetLoader(int width, int height, int columns, int rows, Bitmap source) {
        this.width = source.getWidth()/columns;
        this.height = source.getHeight()/rows;
        this.columns = columns;
        this.rows = rows;

        this.source = source;

        spriteSheet = new Bitmap[rows * columns];

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                spriteSheet[(i * columns) + j] = Bitmap.createBitmap(source, (j * this.width), (i * this.height), this.width, this.height);
                Log.v("Sprite sheet: ", "i: " + i*height + ", j: " + j*width + ", height: " + height + ", width: "+ width);
            }
        }
    }

    public Bitmap[] getSpriteSheet() {
        return spriteSheet;
    }
}
