package com.extremecommandos.pocket_zalcoatl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    ImageView splash;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        intent = new Intent(this.getApplicationContext(), MainActivity.class);

        splash = (ImageView) findViewById(R.id.splash);
        splash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                finish();
            }
        });

    }
}
