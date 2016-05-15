package com.extremecommandos.pocket_zalcoatl;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.extremecommandos.pocket_zalcoatl.SnakeGame.DrawingActivitySnake;
import com.extremecommandos.pocket_zalcoatl.characters.PocketGod;
import com.extremecommandos.pocket_zalcoatl.utils.Animation;
import com.extremecommandos.pocket_zalcoatl.utils.DrawSurface;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by alex on 5/6/16.
 */
public class Game{

    Toolbar actionMenu;
    Button feed, rest, games, info;
    RoundCornerProgressBar life, sleep, fun;
    FloatingActionButton optionsMenu;
    OptionsMenu optionsDialog;
    ViewStub characterSurfaceView;
    DrawSurface drawSurface;
    private boolean soundActive, notifActive;
    private int score =0;

    PocketGod pocketGod;
    Background background;
    MainActivity activity;

    public Game(MainActivity activity) {

        this.activity = activity;


        setSoundOn();
        setNotifOn();


        pocketGod = new PocketGod(activity);
        pocketGod.createGod();

        background = new Background(activity);
        background.createBackground();

        init();

        optionsDialog = new OptionsMenu(activity, this);


        optionsMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionsDialog.show();
            }
        });

        setActionButtonListeners();
        activity.setSupportActionBar(actionMenu);
    }

    protected void init() {
        actionMenu = (Toolbar) activity.findViewById(R.id.toolbar);

        feed = (Button) activity.findViewById(R.id.button_feed);
        rest = (Button) activity.findViewById(R.id.button_rest);
        games = (Button) activity.findViewById(R.id.button_games);
        info = (Button) activity.findViewById(R.id.button_info);

        optionsMenu = (FloatingActionButton) activity.findViewById(R.id.options_menu);

        replaceSurface();

        life = (RoundCornerProgressBar) activity.findViewById(R.id.life_bar);
        sleep = (RoundCornerProgressBar) activity.findViewById(R.id.rest_bar);
        fun = (RoundCornerProgressBar) activity.findViewById(R.id.fun_bar);
    }


    private void setActionButtonListeners() {
        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                life.setProgress(life.getProgress() + 10);
            }
        });

        rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sleep.setProgress(sleep.getProgress() + 10);
            }
        });

        games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fun.setProgress(fun.getProgress() + 10);
                Intent intent = new Intent(activity.getApplicationContext(),DrawingActivitySnake.class);
                startActivity(activity, intent, Bundle.EMPTY);

            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                life.setProgress(0);
                sleep.setProgress(0);
                fun.setProgress(0);
            }
        });
    }

    private void replaceSurface() {
        int index;
        characterSurfaceView = (ViewStub) activity.findViewById(R.id.main_surfaceView);
        ViewGroup vg = (ViewGroup) characterSurfaceView.getParent();
        index = vg.indexOfChild(characterSurfaceView);
        if(vg != null) vg.removeView(characterSurfaceView);
        drawSurface = new DrawSurface(activity.getApplicationContext(), new Animation[] {background.getAnimation(), pocketGod.getAnimation()});
        vg.addView(drawSurface, index);

    }

    boolean getSoundState() {
        return soundActive;
    }

    void setSoundOn() {
        soundActive = true;
    }

    void setSoundOff() {
        soundActive = false;
    }

    boolean getNotifState() {
        return notifActive;
    }

    void setNotifOn() {
        notifActive = true;
    }

    void setNotifOff() {
        notifActive = false;
    }


    protected void onResume() {
        drawSurface.resume();
    }

    protected void onPause() {
        drawSurface.pause();
        pocketGod.pause();
    }

}
