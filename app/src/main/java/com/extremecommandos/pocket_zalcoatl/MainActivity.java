package com.extremecommandos.pocket_zalcoatl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by alex on 3/7/16.
 */
public class MainActivity extends AppCompatActivity {
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new Game(this);

    }


    @Override
    protected void onResume() {
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
        System.out.println("Back on main");
        switch (requestCode) {
            case Game.SNAKE:
                game.drawSurface.resumeAnimations();
                // esta condición se cumple cuando termina un juego
                game.addHearts(data.getExtras().getInt("Score"));
                Log.i("REVIEW", "SCORE NENA " + data.getExtras().getInt("Score"));
                break;
            case Game.FLAPPY:
                game.drawSurface.resumeAnimations();
                // esta condición se cumple cuando termina un juego
                game.addHearts(data.getExtras().getInt("Score"));
                Log.i("REVIEW", "SCORE NENA " + data.getExtras().getInt("Score"));
                break;
        }
    }
}
