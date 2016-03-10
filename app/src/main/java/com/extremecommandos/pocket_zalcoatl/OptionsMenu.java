package com.extremecommandos.pocket_zalcoatl;

import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.view.View;
import android.widget.Button;

/**
 * Created by alex on 3/9/16.
 */
public class OptionsMenu extends AppCompatDialog {

    MainActivity activity;
    Button soundOn, soundOff;
    Button notifOn, notifOff;

    public OptionsMenu(MainActivity activity) {
        super(activity);
        this.activity = activity;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_menu);
        setListeners();
    }

    private void setListeners() {
        soundOn = (Button) findViewById(R.id.sound_on);
        soundOff = (Button) findViewById(R.id.sound_off);

        notifOn = (Button) findViewById(R.id.notif_on);
        notifOff = (Button) findViewById(R.id.notif_off);

        soundOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!activity.getSoundState()) {
                    soundOn.setBackgroundColor(activity.getResources().getColor((R.color.switch_pressed)));
                    soundOff.setBackgroundColor(activity.getResources().getColor((R.color.switch_released)));
                    activity.setSoundOn();
                }
            }
        });

        soundOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activity.getSoundState()) {
                    soundOff.setBackgroundColor(activity.getResources().getColor((R.color.switch_pressed)));
                    soundOn.setBackgroundColor(activity.getResources().getColor((R.color.switch_released)));
                    activity.setSoundOff();
                }
            }
        });

        notifOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!activity.getNotifState()) {
                    notifOn.setBackgroundColor(activity.getResources().getColor((R.color.switch_pressed)));
                    notifOff.setBackgroundColor(activity.getResources().getColor((R.color.switch_released)));
                    activity.setNotifOn();
                }
            }
        });

        notifOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activity.getNotifState()) {
                    notifOff.setBackgroundColor(activity.getResources().getColor((R.color.switch_pressed)));
                    notifOn.setBackgroundColor(activity.getResources().getColor((R.color.switch_released)));
                    activity.setNotifOff();
                }
            }
        });
    }
}
