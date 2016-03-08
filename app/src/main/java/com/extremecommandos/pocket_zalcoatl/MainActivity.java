package com.extremecommandos.pocket_zalcoatl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.akexorcist.roundcornerprogressbar.*;

/**
 * Created by alex on 3/7/16.
 */
public class MainActivity extends AppCompatActivity {

    Toolbar actionMenu;
    Button feed, rest, games, info;
    RoundCornerProgressBar life, sleep, fun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionMenu = (Toolbar) findViewById(R.id.toolbar);

        feed = (Button) findViewById(R.id.button_feed);
        rest = (Button) findViewById(R.id.button_rest);
        games = (Button) findViewById(R.id.button_games);
        info = (Button) findViewById(R.id.button_info);

        life = (RoundCornerProgressBar) findViewById(R.id.life_bar);
        sleep = (RoundCornerProgressBar) findViewById(R.id.rest_bar);
        fun = (RoundCornerProgressBar) findViewById(R.id.fun_bar);

        setActionButtonListeners();
        setSupportActionBar(actionMenu);


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

}
