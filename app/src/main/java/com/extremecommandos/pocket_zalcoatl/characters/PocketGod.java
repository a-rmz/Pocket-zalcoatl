package com.extremecommandos.pocket_zalcoatl.characters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.widget.Button;
import android.widget.TextView;

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
    TextView textView;
    private int sleep, fun, hunger, hearths;

    Bitmap spriteSheet;


    public PocketGod(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        spriteSheet = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.spritesheet);
        SpriteSheetLoader ssl = new SpriteSheetLoader(4 /*columns*/, 2 /*row*/, 4, 4,   spriteSheet);
        characterAnimation = new Animation(ssl.getSpriteSheet(), 3);
        textView = (TextView) mainActivity.findViewById(R.id.textViewHearths);
        textView.setTypeface(Typeface.createFromAsset(mainActivity.getAssets(), "fonts/Tribeca.ttf"));
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

    public int getSleep() {
        return sleep;
    }

    public void setSleep(int sleep) {
        this.sleep = (this.sleep > 100) ? 100 : sleep;
        this.sleep = (this.sleep < 0) ? 0 : this.sleep;
    }

    public int getFun() {
        return fun;
    }

    public void setFun(int fun) {
        this.fun = (this.fun > 100) ? 100 : fun;
        this.fun = (this.fun < 0) ? 0 : this.fun;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = (this.hunger > 100) ? 100 : hunger;
        this.hunger = (this.hunger < 0) ? 0 : this.hunger;
    }

    public int getHearths() {
        return hearths;
    }

    public void setHearths(int hearths) {
        textView.setText(String.valueOf(hearths));
        this.hearths = hearths;
    }
}
