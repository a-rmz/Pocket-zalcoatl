package com.extremecommandos.pocket_zalcoatl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialog;

/**
 * Created by alex on 3/9/16.
 */
public class OptionsMenu extends AppCompatDialog {

    AppCompatActivity activity;

    public OptionsMenu(AppCompatActivity activity) {
        super(activity);
        this.activity = activity;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.options_menu);

    }
}
