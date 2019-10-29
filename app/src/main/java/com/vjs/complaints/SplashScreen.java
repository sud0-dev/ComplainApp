package com.vjs.complaints;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {

    LottieAnimationView animationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        animationView = findViewById(R.id.animation);
        animationView.setRepeatCount(0);
        animationView.playAnimation();

        if (! animationView.isAnimating()) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    animationView.cancelAnimation();
                    Intent myIntent = new Intent(SplashScreen.this, MainActivity.class);
                    SplashScreen.this.startActivity(myIntent);
                    finish();
                }
            }, 3000);
        }
    }
}
