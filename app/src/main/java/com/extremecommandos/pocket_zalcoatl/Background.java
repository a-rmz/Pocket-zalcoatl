package com.extremecommandos.pocket_zalcoatl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.extremecommandos.pocket_zalcoatl.utils.Animation;
import com.extremecommandos.pocket_zalcoatl.utils.SpriteSheetLoader;

/**
 * Created by alex on 5/5/16.
 */
public class Background {

    Animation backgroundAnimation;

    public Background(MainActivity activity) {
        Bitmap spriteSheet = BitmapFactory.decodeResource(activity.getResources(), R.drawable.main_bg);
        SpriteSheetLoader ssl = new SpriteSheetLoader(1 /*columns*/, 1 /*row*/, spriteSheet);
        backgroundAnimation = new Animation(ssl.getSpriteSheet(), 1);
    }

    public Animation getAnimation() {
        return this.backgroundAnimation;
    }

    public void createBackground() {
        backgroundAnimation.restart();
    }

}
