package com.extremecommandos.pocket_zalcoatl.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;

import com.extremecommandos.pocket_zalcoatl.MainActivity;
import com.extremecommandos.pocket_zalcoatl.R;

/**
 * Created by alex on 3/10/16.
 */
public class SpriteSheetLoader {

    public static final int QUETZALCOATL = 0;

    Bitmap source;

    int width, height, rows, columns, MODE;

    Bitmap[] spriteSheet;

    public SpriteSheetLoader(int width, int height, int columns, int rows, Bitmap source) {
        this.width = width;
        this.height = height;
        this.columns = columns;
        this.rows = rows;

        this.source = source;

        spriteSheet = new Bitmap[rows * columns];

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                spriteSheet[(i * columns) + j] = Bitmap.createBitmap(source, (j * width), (i * height), width, height);
            }
        }
    }

    public Bitmap[] getSpriteSheet() {
        return spriteSheet;
    }
}
