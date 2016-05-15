package com.extremecommandos.pocket_zalcoatl;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.extremecommandos.pocket_zalcoatl.characters.PocketGod;
import com.extremecommandos.pocket_zalcoatl.utils.Animation;
import com.extremecommandos.pocket_zalcoatl.utils.DrawSurface;

/**
 * Created by alex on 3/7/16.
 */
public class MainActivity extends AppCompatActivity {
    //int score =0;
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new Game(this);
        //score= 1;
    }


    @Override
    protected void onResume() {
        //this.score = getIntent().getParcelableExtra("Score");
        super.onResume();
       game.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        game.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Game.SNAKE:
                game.drawSurface.resumeAnimations();
                break;
        }
    }
}
