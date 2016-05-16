package com.extremecommandos.pocket_zalcoatl.SnakeGame;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.media.MediaPlayer;


import com.extremecommandos.pocket_zalcoatl.MainActivity;
import com.extremecommandos.pocket_zalcoatl.R;


public class DrawingActivitySnake extends AppCompatActivity {
    RelativeLayout drawRelativeLayout;
    MySurfaceViewSnake mySurfaceView;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        drawRelativeLayout = (RelativeLayout) findViewById(R.id.drawLayout);
        mySurfaceView = new MySurfaceViewSnake(getApplicationContext(), this.getResources(), this);
        drawRelativeLayout.addView(mySurfaceView);
        mediaPlayer = MediaPlayer.create(this, R.raw.Artifact_The_Dark_Contenent);
        mediaPlayer.start();
    }

    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            drawRelativeLayout.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        }
    }

    public void returnToMainActivity(int score) {
        mediaPlayer.stop();
        Intent intent = new Intent(DrawingActivitySnake.this, MainActivity.class);
        intent.putExtra("Score", score);
        setResult(0, intent);
        finish();
    }

}
