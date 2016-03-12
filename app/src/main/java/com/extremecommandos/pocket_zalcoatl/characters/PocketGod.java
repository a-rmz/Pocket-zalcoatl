package com.extremecommandos.pocket_zalcoatl.characters;

import com.extremecommandos.pocket_zalcoatl.MainActivity;
import com.extremecommandos.pocket_zalcoatl.utils.Animation;

/**
 * Created by alex on 3/10/16.
 */
public class PocketGod {

    private String name;
    MainActivity mainActivity;
    Animation characterAnimation;


    public PocketGod(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        characterAnimation = new Animation(mainActivity, mainActivity.getCharacterSurfaceHolder());
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
}
