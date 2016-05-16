package com.extremecommandos.pocket_zalcoatl;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
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


/**
 * Created by alex on 3/7/16.
 */
public class MainActivity extends AppCompatActivity {
    Game game;


    Intent intent;
    FeedService myService;
    boolean isBound = false;

    ServiceConnection myConnection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            FeedService.LocalBinder binder = (FeedService.LocalBinder) service;
            myService = binder.getService();
            isBound = true;
        }

        public void onServiceDisconnected(ComponentName arg0) {
            isBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new Game(this);


        ///////////////////////////////CONECT TO THE SERVICE/////////////////////////
            //poner un booleano en en preferencias para que inici el servicio sólo una vez
            //myService = new FeedService();
            //intent = new Intent(this, FeedService.class);
            //bindService(intent, myConnection, Context.BIND_AUTO_CREATE);
            //Log.i("TU_PUEDES_NENA", "Cuenta " + myService.getCount());
        /////////////////////////////////////////////////////////////////////////////

        SharedPreferences shared = this.getPreferences(Context.MODE_PRIVATE);
        game.pocketGod.setHearths(shared.getInt(getString(R.string.hearths), 50));
        game.pocketGod.setFun(shared.getInt(getString(R.string.fun), 50));
        game.pocketGod.setHunger(shared.getInt(getString(R.string.hunger), 50));
        game.pocketGod.setSleep(shared.getInt(getString(R.string.sleep), 50));
        game.updateStatBars();
        long savedTime = shared.getLong(getString(R.string.date), 0);

    }


    @Override
    protected void onResume() {
        if(intent!=null)
            stopService(intent);

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
        System.out.println("Back on main");
        switch (requestCode) {
            case Game.SNAKE:
                game.drawSurface.resumeAnimations();
                // esta condición se cumple cuando termina un juego
                game.addHearts(data.getExtras().getInt("Score"));
                break;
            case Game.FLAPPY:
                game.drawSurface.resumeAnimations();
                // esta condición se cumple cuando termina un juego
                game.addHearts(data.getExtras().getInt("Score"));
                break;
        }
    }
    /*
    private boolean isServiceRunning(Class<?> serviceClass){
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for(ActivityManager.RunningServiceInfo service: manager.getRunningServices(Integer.MAX_VALUE)){
            if(serviceClass.getName().equals(service.service.getClassName())){
                return true;
            }
        }

        return false;
    }*/
}
