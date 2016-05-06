package com.extremecommandos.pocket_zalcoatl.characters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.extremecommandos.pocket_zalcoatl.MainActivity;
import com.extremecommandos.pocket_zalcoatl.R;
import com.extremecommandos.pocket_zalcoatl.utils.Animation;
import com.extremecommandos.pocket_zalcoatl.utils.SpriteSheetLoader;

/**
 * Created by alex on 3/10/16.
 */
public class PocketGod {

    private String name;
    MainActivity mainActivity;
    Animation characterAnimation;

    Bitmap spriteSheet;


    public PocketGod(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        spriteSheet = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.spritesheet_small_right);
        SpriteSheetLoader ssl = new SpriteSheetLoader(4 /*columns*/, 1 /*row*/, spriteSheet);
        characterAnimation = new Animation(mainActivity, ssl.getSpriteSheet(), 10);
    }

    public void createGod() {
        characterAnimation.restart();
    }

    public void pause() {
        characterAnimation.pause();
    }

    public void stop() {
        characterAnimation.stop();
    }

    public void restart() {
        characterAnimation.restart();
    }

    public Animation getAnimation() {
        return this.characterAnimation;
    }
}
