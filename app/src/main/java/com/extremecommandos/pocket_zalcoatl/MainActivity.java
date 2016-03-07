package com.extremecommandos.pocket_zalcoatl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by alex on 3/7/16.
 */
public class MainActivity extends AppCompatActivity {

    Toolbar actionMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionMenu = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(actionMenu);
    }

}
