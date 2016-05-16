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
    private long count1 =0;
    private long count2 =0;
    private long count3 =0;
    private int countFeed=0;
    private int countTired=0;
    private int countBored=0;
    Notification notification, notification2, notification3;

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

        Intent intent1 = new Intent();
        final PendingIntent pIntent = PendingIntent.getActivity(this,0,intent1,0);
        Intent intent2 = new Intent();
        final PendingIntent pIntent2 = PendingIntent.getActivity(this,0,intent1,0);
        Intent intent3 = new Intent();
        final PendingIntent pIntent3 = PendingIntent.getActivity(this,0,intent1,0);

       thread = new Thread(){
            public void run(){

             while (running) {

                 count1++;
                 count2++;
                 count3++;


                 if(count1==10){
                   notification = new Notification.Builder(getApplicationContext())
                           .setContentTitle(getResources().getString(R.string.TitleHungry))
                           .setContentText(getResources().getString(R.string.TextHungry))
                           .setSmallIcon(R.drawable.feed)
                            .setContentIntent(pIntent).getNotification();
                     NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                     notificationManager.notify(0,notification);
                     count1=0;
                     countFeed++;
                 }
                 if(count2==15){
                     notification2 = new Notification.Builder(getApplicationContext())
                             .setContentTitle(getResources().getString(R.string.TitleBored))
                             .setContentText(getResources().getString(R.string.TextBored))
                             .setSmallIcon(R.drawable.play)
                             .setContentIntent(pIntent2).getNotification();
                     NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                     notificationManager.notify(1,notification2);
                     count2=0;
                     countBored++;
                 }
                 if(count3==20){
                     notification3 = new Notification.Builder(getApplicationContext())
                             .setContentTitle(getResources().getString(R.string.TitleTired))
                             .setContentText(getResources().getString(R.string.TextTired))
                             .setSmallIcon(R.drawable.sleep)
                             .setContentIntent(pIntent3).getNotification();
                     NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                     notificationManager.notify(2,notification3);
                     count3 =0;
                     countTired++;
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
        if(thread != null) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            super.onDestroy();
        }
    }

    public int getCountFeed(){
        return countFeed;
    }

    public int getCountBored(){
        return countBored;
    }

    public int getCountTired(){
        return countTired;
    }

    public void setCountNewConuts(int newCount){
        this.countFeed= newCount;
        this.countTired= newCount;
        this.countBored= newCount;
    }

    }
