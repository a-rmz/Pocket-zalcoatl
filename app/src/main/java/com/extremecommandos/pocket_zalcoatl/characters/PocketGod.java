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

    private int STRLEN = 20;

    private String name;
    MainActivity mainActivity;
    Animation characterAnimation;
    private int life, fun, hunger, hearths;

    Bitmap spriteSheet;


    public PocketGod(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        spriteSheet = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.spritesheet);
        SpriteSheetLoader ssl = new SpriteSheetLoader(4 /*columns*/, 2 /*row*/, 4, 4,   spriteSheet);
        characterAnimation = new Animation(ssl.getSpriteSheet(), 3);
        setLife(100);
        setFun(100);
        setHunger(100);
        setHearths(0);
    }

    public void createGod() {
        characterAnimation.restart();
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

    public void setName(String name) {
        if(name.length() > STRLEN) {
            this.name = name.substring(0, STRLEN);
        } else {
            this.name = name;
        }
    }

    public String getName() {
        return name;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getFun() {
        return fun;
    }

    public void setFun(int fun) {
        this.fun = fun;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public int getHearths() {
        return hearths;
    }

    public void setHearths(int hearths) {
        this.hearths = hearths;
    }
}
