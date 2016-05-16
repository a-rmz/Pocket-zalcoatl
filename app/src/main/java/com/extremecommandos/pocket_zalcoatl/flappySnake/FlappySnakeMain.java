package com.extremecommandos.pocket_zalcoatl.flappySnake;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.extremecommandos.pocket_zalcoatl.MainActivity;
import com.extremecommandos.pocket_zalcoatl.flappySnake.game.GameSurface;
import com.extremecommandos.pocket_zalcoatl.R;


public class FlappySnakeMain extends AppCompatActivity {

    FrameLayout frameLayout;
    GameSurface gameSurface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_flappy_snake_main);

        frameLayout = (FrameLayout) (findViewById(R.id.screen));
        gameSurface = new GameSurface(this, getResources());
        frameLayout.removeAllViews();
        frameLayout.addView(gameSurface);


    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            frameLayout.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        gameSurface.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameSurface.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
        System.out.println("onStop, finishing: " + isFinishing());
    }

    public void returnToMainActivity(int score) {
        System.out.println("Return 2 main Main");
        Intent intent = new Intent(FlappySnakeMain.this, MainActivity.class);
        intent.putExtra("Score", score);
        setResult(0, intent);
        finish();
        System.out.println("returnToMain, finishing: " + isFinishing());
    }
}
