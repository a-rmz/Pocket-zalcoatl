package com.extremecommandos.pocket_zalcoatl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialog;
import android.view.View;
import android.widget.Button;

/**
 * Created by alex on 3/9/16.
 */
public class OptionsMenu extends AppCompatDialog {

    AppCompatActivity activity;
    Button soundOn, soundOff;
    boolean soundState;

    public OptionsMenu(AppCompatActivity activity) {
        super(activity);
        this.activity = activity;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_menu);
        soundState = true;
        setListeners();
    }

    private void setListeners() {
        soundOn = (Button) findViewById(R.id.sound_on);
        soundOff = (Button) findViewById(R.id.sound_off);

        soundOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!soundState) {
                    soundOn.setBackgroundColor(activity.getResources().getColor((R.color.switch_pressed)));
                    soundOff.setBackgroundColor(activity.getResources().getColor((R.color.switch_released)));
                    soundState = true;
                }
            }
        });

        soundOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundState) {
                    soundOff.setBackgroundColor(activity.getResources().getColor((R.color.switch_pressed)));
                    soundOn.setBackgroundColor(activity.getResources().getColor((R.color.switch_released)));
                    soundState = false;
                }
            }
        });
    }
}
