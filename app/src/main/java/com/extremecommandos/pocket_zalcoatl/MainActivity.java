package com.extremecommandos.pocket_zalcoatl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.design.widget.Snackbar;
import android.widget.Button;

import com.akexorcist.roundcornerprogressbar.*;

/**
 * Created by alex on 3/7/16.
 */
public class MainActivity extends AppCompatActivity {

    Toolbar actionMenu;
    Button feed, rest, games, info;
    IconRoundCornerProgressBar life, sleep, fun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionMenu = (Toolbar) findViewById(R.id.toolbar);

        feed = (Button) findViewById(R.id.button_feed);
        rest = (Button) findViewById(R.id.button_rest);
        games = (Button) findViewById(R.id.button_games);
        info = (Button) findViewById(R.id.button_info);

        life = (IconRoundCornerProgressBar) findViewById(R.id.life_bar);
        sleep = (IconRoundCornerProgressBar) findViewById(R.id.rest_bar);
        fun = (IconRoundCornerProgressBar) findViewById(R.id.fun_bar);

        setActionButtonListeners();
        setSupportActionBar(actionMenu);


    }


    private void setActionButtonListeners() {
        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                life.setProgress(life.getProgress() + 10);
                Snackbar.make(findViewById(R.id.main_screen), "Clicked feed!", Snackbar.LENGTH_SHORT).show();
            }
        });

        rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(life.getProgress() > 0) life.setProgress(life.getProgress() - 10);
                Snackbar.make(findViewById(R.id.main_screen), "Clicked rest!", Snackbar.LENGTH_SHORT).show();
            }
        });

        games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(findViewById(R.id.main_screen), "Clicked games!", Snackbar.LENGTH_SHORT).show();
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(findViewById(R.id.main_screen), "Clicked info!", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

}
