package com.extremecommandos.pocket_zalcoatl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
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
        myService = new FeedService();
        intent = new Intent(this, FeedService.class);
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE);
        //Log.i("TU_PUEDES_NENA", "Cuenta " + myService.getCount());
        startService(intent);
        //stopService(new Intent(getBaseContext(), FeedService.class));
        ///////////////////AQUI TERMINA LA LLAMADA AL SERVICIO////////////////////

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
                break;
            case Game.FLAPPY:
                game.drawSurface.resumeAnimations();
                // esta condición se cumple cuando termina un juego
                game.addHearts(data.getExtras().getInt("Score"));
                break;
        }
    }
}
