package com.extremecommandos.pocket_zalcoatl;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Binder;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.extremecommandos.pocket_zalcoatl.SnakeGame.DrawingActivitySnake;
import com.extremecommandos.pocket_zalcoatl.characters.PocketGod;
import com.extremecommandos.pocket_zalcoatl.flappySnake.FlappySnakeMain;
import com.extremecommandos.pocket_zalcoatl.utils.Animation;
import com.extremecommandos.pocket_zalcoatl.utils.DrawSurface;

/**
 * Created by alex on 5/6/16.
 */
public class Game{

    public static final int SNAKE = 1;
    public static final int FLAPPY = 2;

    Toolbar actionMenu;
    Button feed, rest, games, info, gameFlappy, gameSnake;
    Toolbar gamesToolbar;
    RoundCornerProgressBar life, sleep, fun;
    FloatingActionButton optionsMenu;
    OptionsMenu optionsDialog;
    ViewStub characterSurfaceView;
    DrawSurface drawSurface;
    private boolean soundActive, notifActive, gamesActive;
    private int score =0;

    public PocketGod pocketGod;
    Background background;
    MainActivity activity;

    public Game(final MainActivity activity) {

        this.activity = activity;


        if(activity.soundActive) {
            setSoundOn();
        } else {
            setSoundOff();
        }
        setNotifOn();

        gamesActive = false;

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
        gameSnake = (Button) activity.findViewById(R.id.game_snake);
        gameFlappy = (Button) activity.findViewById(R.id.game_flappy);

        gamesToolbar = (Toolbar) activity.findViewById(R.id.toolbar_games);

        optionsMenu = (FloatingActionButton) activity.findViewById(R.id.options_menu);

        replaceSurface();

        life = (RoundCornerProgressBar) activity.findViewById(R.id.life_bar);
        sleep = (RoundCornerProgressBar) activity.findViewById(R.id.rest_bar);
        fun = (RoundCornerProgressBar) activity.findViewById(R.id.fun_bar);
    }

    protected void updateStatBars() {
        life.setProgress(pocketGod.getHunger());
        fun.setProgress(pocketGod.getFun());
        sleep.setProgress(pocketGod.getSleep());

        if(pocketGod.getHunger() > 60) {
            life.setProgressColor(activity.getResources().getColor(R.color.normal));
        } else if(pocketGod.getHunger() <= 60 && pocketGod.getHunger() > 25) {
            life.setProgressColor(activity.getResources().getColor(R.color.warning));
        } else {
            life.setProgressColor(activity.getResources().getColor(R.color.critic));
        }

        if(pocketGod.getFun() > 60) {
            fun.setProgressColor(activity.getResources().getColor(R.color.normal));
        } else if(pocketGod.getFun() <= 60 && pocketGod.getFun() > 25) {
            fun.setProgressColor(activity.getResources().getColor(R.color.warning));
        } else {
            fun.setProgressColor(activity.getResources().getColor(R.color.critic));
        }

        if(pocketGod.getSleep() > 60) {
            sleep.setProgressColor(activity.getResources().getColor(R.color.normal));
        } else if(pocketGod.getSleep() <= 60 && pocketGod.getSleep() > 25) {
            sleep.setProgressColor(activity.getResources().getColor(R.color.warning));
        } else {
            sleep.setProgressColor(activity.getResources().getColor(R.color.critic));
        }
    }


    private void setActionButtonListeners() {
        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pocketGod.setHunger(pocketGod.getHunger() + 5);
                pocketGod.setHearths(pocketGod.getHearths() - 5);
                updateStatBars();
            }
        });

        rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(PocketGod.isSleeping) {
                    pocketGod.awake();
                } else {
                    pocketGod.goToSleep();
                }
                updateStatBars();
            }
        });

        games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamesActive = !gamesActive;
                if (gamesActive) {
                    gamesToolbar.setVisibility(View.VISIBLE);
                    gameSnake.setClickable(true);
                    gameFlappy.setClickable(true);
                } else {
                    gamesToolbar.setVisibility(View.INVISIBLE);
                    gameSnake.setClickable(false);
                    gameFlappy.setClickable(false);
                }
                updateStatBars();
            }
        });

        gameFlappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawSurface.pauseAnimations();
                Intent intent = new Intent(activity.getApplicationContext(), DrawingActivitySnake.class);
                activity.startActivityForResult(intent, SNAKE);
                pocketGod.setFun(pocketGod.getFun() + 5);
            }
        });

        gameSnake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawSurface.pauseAnimations();
                Intent intent = new Intent(activity.getApplicationContext(), FlappySnakeMain.class);
                activity.startActivityForResult(intent, SNAKE);
                pocketGod.setFun(pocketGod.getFun() + 5);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                pocketGod.setSleep(0);
//                pocketGod.setFun(0);
//                pocketGod.setHunger(0);
            }
        });
    }

    public void isHungry(){
        life.setProgress(life.getProgress() - 1);
    }

    public void isBored(){
        fun.setProgress(fun.getProgress() - 1);
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
        if(activity.mediaPlayer != null) {
            activity.mediaPlayer.start();
        }
    }

    void setSoundOff() {
        soundActive = false;
        if(activity.mediaPlayer != null) {
            activity.mediaPlayer.pause();
        }

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
    }

    public  void addHearts(int hearts){
        pocketGod.setHearths(pocketGod.getHearths() + hearts);

    }

    public int getHearths() {
        return pocketGod.getHearths();
    }

    public int getSleep() {
        return pocketGod.getSleep();
    }

    public int getFun() {
        return pocketGod.getFun();
    }

    public int getHunger() {
        return pocketGod.getHunger();
    }


}
