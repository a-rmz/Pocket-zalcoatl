package com.extremecommandos.pocket_zalcoatl;

import android.app.Dialog;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.akexorcist.roundcornerprogressbar.*;
import com.extremecommandos.pocket_zalcoatl.utils.Animation;
import com.extremecommandos.pocket_zalcoatl.utils.DrawSurface;

/**
 * Created by alex on 3/7/16.
 */
public class MainActivity extends AppCompatActivity {

    Toolbar actionMenu;
    Button feed, rest, games, info;
    RoundCornerProgressBar life, sleep, fun;
    FloatingActionButton optionsMenu;
    OptionsMenu optionsDialog;
    SurfaceView characterSurfaceView;
    private boolean soundActive, notifActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSoundOn();
        setNotifOn();

        init();

        optionsDialog = new OptionsMenu(this);


        optionsMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionsDialog.show();
            }
        });

        setActionButtonListeners();
        setSupportActionBar(actionMenu);

        Animation animation = new Animation(this);

    }

    protected void init() {
        actionMenu = (Toolbar) findViewById(R.id.toolbar);

        feed = (Button) findViewById(R.id.button_feed);
        rest = (Button) findViewById(R.id.button_rest);
        games = (Button) findViewById(R.id.button_games);
        info = (Button) findViewById(R.id.button_info);

        optionsMenu = (FloatingActionButton) findViewById(R.id.options_menu);

        characterSurfaceView = (SurfaceView) findViewById(R.id.surfaceView);

        life = (RoundCornerProgressBar) findViewById(R.id.life_bar);
        sleep = (RoundCornerProgressBar) findViewById(R.id.rest_bar);
        fun = (RoundCornerProgressBar) findViewById(R.id.fun_bar);
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

    public SurfaceHolder getCharacterSurfaceHolder() {
        return characterSurfaceView.getHolder();
    }

}
