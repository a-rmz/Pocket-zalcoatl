package com.extremecommandos.pocket_zalcoatl.utils;

import android.app.Service;
import android.content.Intent;
import android.os.*;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by kevin on 15/05/2016.
 */
public class FeedService extends Service {
    private Thread thread;
    private boolean running;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        running = true;
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
       thread = new Thread(){
            public void run(){
            //aquí haces tu cagadero com en el game loop
             while (running) {

                // paara saber si funciona
                 Log.i("WARNING", "Servicio Trabajando");

                 try {
                     Thread.sleep(1000);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }

            }
        };
        thread.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy(){
        running= false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.onDestroy();
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_LONG).show();
    }

}
