package com.vjs.complaints;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.lottie.LottieAnimationView;
import com.vjs.complaints.admin.AdminPage;
import com.vjs.complaints.students.StudentsPage;

public class SplashScreen extends AppCompatActivity {

    LottieAnimationView animationView;
    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        animationView = findViewById(R.id.animation);
        animationView.setRepeatCount(2);
        animationView.playAnimation();

        if (! animationView.isAnimating()) {
            Handler handler = new Handler();

            SharedPreferences sharedPreferences = getSharedPreferences("user.details", Context.MODE_PRIVATE);
            username = sharedPreferences.getString("user", "user");
            password = sharedPreferences.getString("pass", "pass");

            assert password != null;
            if(username.equals("student") && password.equals("student")) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        animationView.cancelAnimation();
                        Intent intent = new Intent(SplashScreen.this, StudentsPage.class);
                        SplashScreen.this.startActivity(intent);
                        finish();
                    }
                }, 3000);
            }

            else if(username.equals("admin") && password.equals("admin")) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        animationView.cancelAnimation();
                        Intent intent = new Intent(SplashScreen.this, AdminPage.class);
                        SplashScreen.this.startActivity(intent);
                        finish();
                    }
                }, 3000);
            }
            else {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        animationView.cancelAnimation();
                        Intent myIntent = new Intent(SplashScreen.this, welcome.class);
                        SplashScreen.this.startActivity(myIntent);
                        finish();
                    }
                }, 3000);
            }
        }
    }
}
