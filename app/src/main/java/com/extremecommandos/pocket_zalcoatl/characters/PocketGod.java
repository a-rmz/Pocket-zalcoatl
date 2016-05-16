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

import java.util.concurrent.TimeUnit;

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
    public static boolean isSleeping;
    private long sleepTime;
    Bitmap spriteSheet;


    public PocketGod(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        spriteSheet = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.spritesheet);
        SpriteSheetLoader ssl = new SpriteSheetLoader(4 /*columns*/, 4 /*row*/, 4, 4,   spriteSheet);
        characterAnimation = new Animation(ssl.getSpriteSheet(), 5);
        textView = (TextView) mainActivity.findViewById(R.id.textViewHearths);
        textView.setTypeface(Typeface.createFromAsset(mainActivity.getAssets(), "fonts/Tribeca.ttf"));
        isSleeping = false;
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
        if(getHearths() > 0) {
            this.hunger = (this.hunger > 100) ? 100 : hunger;
        }
        this.hunger = (this.hunger < 0) ? 0 : this.hunger;
    }

    public int getHearths() {
        return hearths;
    }

    public void setHearths(int hearths) {
        if(hearths <= 0) {
            this.hearths = 0;
        } else {
            this.hearths = hearths;
        }
        textView.setText(String.valueOf(hearths));
    }


    public void goToSleep() {
        isSleeping = true;
        sleepTime = System.currentTimeMillis();
    }
    public void awake() {
        isSleeping = false;
        long timeSlept = System.currentTimeMillis() - sleepTime;
        long minutesSlept = TimeUnit.MILLISECONDS.toMinutes(timeSlept);
        int sleepPoints = 0;
        for(int i = 0; i < minutesSlept; i++) {
            if(i % 10 == 0) sleepPoints++;
        }
        setSleep(getSleep() + (5*sleepPoints));
    }

}
