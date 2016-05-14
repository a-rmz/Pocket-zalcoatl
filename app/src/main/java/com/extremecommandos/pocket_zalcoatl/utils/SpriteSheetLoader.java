package com.extremecommandos.pocket_zalcoatl.utils;

import android.graphics.Bitmap;

/**
 * Created by alex on 3/10/16.
 */
public class SpriteSheetLoader {

    Bitmap source;

    int width, height, rows, columns;

    Bitmap[] spriteSheet;

    public SpriteSheetLoader(int columns, int rows, Bitmap source) {
        this.width = source.getWidth() / columns;
        this.height = source.getHeight() / rows;
        this.columns = columns;
        this.rows = rows;

        this.source = source;

        spriteSheet = new Bitmap[rows * columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

//                spriteSheet[(i * columns) + j] = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888);

                spriteSheet[(i * columns) + j] = Bitmap.createBitmap(source, (j * this.width), (i * this.height), this.width, this.height);
                spriteSheet[(i * columns) + j].setHasAlpha(true);
//                spriteSheet[(i * columns) + j] = Bitmap.createScaledBitmap(spriteSheet[(i * columns) + j], this.width, this.height, true);

            }
        }
    }


    public Bitmap[] getSpriteSheet() {
        return spriteSheet;
    }
}
