package com.extremecommandos.pocket_zalcoatl.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.*;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.extremecommandos.pocket_zalcoatl.R;

import java.io.FileDescriptor;

/**
 * Created by kevin on 15/05/2016.
 */
public class FeedService extends Service {

    private final IBinder iBinder = new LocalBinder();


    private Thread thread;
    private boolean running;
    private long count =0;
    Notification notification;

    public FeedService(){

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    public class LocalBinder extends Binder{

         public FeedService getService(){
            return  FeedService.this;
        }

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        running = true;
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();

        Intent intent1 = new Intent();
        final PendingIntent pIntent = PendingIntent.getActivity(this,0,intent1,0);

       thread = new Thread(){
            public void run(){

             while (running) {

                // paara saber si funciona
                 Log.i("WARNING", "Servicio Trabajando");
                 count++;


                 if(count==10){
                   notification = new Notification.Builder(getApplicationContext())
                           .setContentTitle(getResources().getString(R.string.TitleHungry))
                           .setContentText(getResources().getString(R.string.TextHungry))
                           .setSmallIcon(R.drawable.feed)
                            .setContentIntent(pIntent).getNotification();
                     NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                     notificationManager.notify(0,notification);
                 }
                 if(count==15){
                     notification = new Notification.Builder(getApplicationContext())
                             .setContentTitle(getResources().getString(R.string.TitleBored))
                             .setContentText(getResources().getString(R.string.TextBored))
                             .setSmallIcon(R.drawable.feed)
                             .setContentIntent(pIntent).getNotification();
                     NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                     notificationManager.notify(0,notification);
                 }
                 if(count==20){
                     notification = new Notification.Builder(getApplicationContext())
                             .setContentTitle(getResources().getString(R.string.TitleTired))
                             .setContentText(getResources().getString(R.string.TextTired))
                             .setSmallIcon(R.drawable.feed)
                             .setContentIntent(pIntent).getNotification();
                     NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                     notificationManager.notify(0,notification);
                 }

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
