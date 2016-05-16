package com.extremecommandos.pocket_zalcoatl.flappySnake.blocks;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.extremecommandos.pocket_zalcoatl.R;
import com.extremecommandos.pocket_zalcoatl.flappySnake.utils.Dimensions;
import com.extremecommandos.pocket_zalcoatl.flappySnake.utils.Position;

import java.util.Random;

/**
 * Created by alex on 4/21/16.
 */
public class Column {

    protected Position pos;
    private Dimensions dimensions;
    public Bitmap image;
    private Random rand;

    public Column(Dimensions dimensions, Resources resources, int dx, int displacement) {
        this.dimensions = dimensions;
        rand = new Random(System.currentTimeMillis() + System.nanoTime());

        this.image = BitmapFactory.decodeResource(resources, defineSize());
        init(dx, displacement);
    }

    private void init(int dx, int displacement) {
        pos = new Position(
                dimensions.getWidthPx() + rand.nextInt(dimensions.getWidthPx()) * (4 / displacement),
                rand.nextInt(dimensions.getHeightPx()),
                dimensions.dpToPx(-dx),
                0,
                dimensions.getHeightPx(),
                dimensions.getWidthPx(),
                0);
        pos.setDimens(image);


    }

    public int defineSize() {
        int pillar = 0;
        switch (rand.nextInt(4) + 2) {
            case 2:
                pillar = R.drawable.pilar_2;
                break;
            case 3:
                pillar = R.drawable.pilar_3;
                break;
            case 4:
                pillar = R.drawable.pilar_4;
                break;
            case 5:
                pillar = R.drawable.pilar_5;
                break;
            case 6:
                pillar = R.drawable.pilar_6;
                break;
        }
        return pillar;
    }

    public Position getPos() {
        return pos;
    }

    public void update() {
        pos.movePosX(pos.getMaxX(), 1, true);
    }

    public Rect getCollider() {
        return new Rect(pos.getPosX(), pos.getPosY(), pos.getPosX() + pos.getWidth(), pos.getPosY() + pos.getHeight());
    }


}

