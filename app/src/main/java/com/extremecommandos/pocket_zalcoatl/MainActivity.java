package com.extremecommandos.pocket_zalcoatl;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.extremecommandos.pocket_zalcoatl.SnakeGame.Score;
import com.extremecommandos.pocket_zalcoatl.characters.PocketGod;
import com.extremecommandos.pocket_zalcoatl.utils.Animation;
import com.extremecommandos.pocket_zalcoatl.utils.DrawSurface;
import com.extremecommandos.pocket_zalcoatl.utils.FeedService;


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

        SharedPreferences shared = this.getPreferences(Context.MODE_PRIVATE);
        game.pocketGod.setHearths(shared.getInt(getString(R.string.hearths), 50));
        game.pocketGod.setFun(shared.getInt(getString(R.string.fun), 50));
        game.pocketGod.setHunger(shared.getInt(getString(R.string.hunger), 50));
        game.pocketGod.setLife(shared.getInt(getString(R.string.life), 50));
        long savedTime = shared.getLong(getString(R.string.date), 0);


        ///////////////////AQUI SE INICIA EL SERVICIO//////////////////
        //el servicio es FeedService en la carpeta Utils
        //startService(new Intent(getBaseContext(), FeedService.class));
        ///////////////////CON ESTA LÍNEA DETIENES EL SERVICIO////////////////////
        //stopService(new Intent(getBaseContext(), FeedService.class));
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
    protected void onStop() {
        super.onStop();
        SharedPreferences shared = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putInt(getString(R.string.hearths), game.getHearths());
        editor.putInt(getString(R.string.fun), game.getFun());
        editor.putInt(getString(R.string.hunger), game.getHunger());
        editor.putInt(getString(R.string.life), game.getLife());
        editor.putLong(getString(R.string.date), System.currentTimeMillis());
        editor.apply();
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
