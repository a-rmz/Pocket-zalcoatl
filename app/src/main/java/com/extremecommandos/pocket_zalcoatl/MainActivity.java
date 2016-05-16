package com.extremecommandos.pocket_zalcoatl;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Binder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.os.IBinder;
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

import java.util.concurrent.TimeUnit;


/**
 * Created by alex on 3/7/16.
 */
public class MainActivity extends AppCompatActivity {
    Game game;


    Intent intent;
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new Game(this);

        SharedPreferences shared = this.getPreferences(Context.MODE_PRIVATE);
        int tmpHearhts = shared.getInt(getString(R.string.hearths), 50);
        int tmpFun = shared.getInt(getString(R.string.fun), 50);
        int tmpHunger = shared.getInt(getString(R.string.hunger), 50);
        int tmpSleep = shared.getInt(getString(R.string.sleep), 50);

        long savedTime = shared.getLong(getString(R.string.date), 0);
        long actual = System.currentTimeMillis();
        long minutes = TimeUnit.MILLISECONDS.toSeconds(actual - savedTime);

        for(int i = 0; i < minutes; i++) {
            if(i % 30 == 0) {
                tmpHunger = tmpHunger - 5*(i/30);
            }
            if(i % 45 == 0) {
                tmpFun = tmpFun - 5*(i/45);
            }
            if(i % 60 == 0) {
                tmpSleep = tmpSleep - 10*(i/60);
            }
        }


        game.pocketGod.setHearths(tmpHearhts);
        game.pocketGod.setFun(tmpFun);
        game.pocketGod.setHunger(tmpHunger);
        game.pocketGod.setSleep(tmpSleep);
        game.updateStatBars();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer = MediaPlayer.create(this, R.raw.jungle);
        mediaPlayer.start();

            intent = new Intent(this, FeedService.class);
            stopService(intent);

        game.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        game.onPause();
    }

    @Override
    protected void onStop() {
        mediaPlayer.stop();
        intent = new Intent(this, FeedService.class);
        startService(intent);
        super.onStop();
        SharedPreferences shared = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putInt(getString(R.string.hearths), game.getHearths());
        editor.putInt(getString(R.string.fun), game.getFun());
        editor.putInt(getString(R.string.hunger), game.getHunger());
        editor.putInt(getString(R.string.sleep), game.getSleep());
        editor.putLong(getString(R.string.date), System.currentTimeMillis());
        editor.apply();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        game.drawSurface.resumeAnimations();
        game.addHearts(data.getExtras().getInt("Score"));
    }

}
