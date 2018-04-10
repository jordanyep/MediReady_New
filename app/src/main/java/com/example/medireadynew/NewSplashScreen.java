package com.example.medireadynew;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NewSplashScreen extends AppCompatActivity {
    private static int SPLASH_TIMEOUT = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splashScreenIntent = new Intent(NewSplashScreen.this, NewHomeTwo.class);
                startActivity(splashScreenIntent);
                finish();
            }
        },SPLASH_TIMEOUT);
    }
}
