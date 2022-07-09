package com.dreamyleague.prosoccer.activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;

import com.dreamyleague.prosoccer.activities.DashboardActivity;

public class SplashActivity extends AppCompatActivity {

    private static long SPLASH_TIME_OUT = 3000L;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        gotoMainBoard();
    }
    private void gotoMainBoard() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, DashboardActivity.class));
//            finish();

        }, SPLASH_TIME_OUT);
    }

}

