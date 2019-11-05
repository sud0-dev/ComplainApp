package com.vjs.complaints;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class welcome extends AppCompatActivity {

    private boolean backPressToExit = false;
    String exit_msg = "Press back again to exit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        TextView login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(welcome.this, login.class);
                welcome.this.startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (backPressToExit) {
            super.onBackPressed();
            return;
        }
        this.backPressToExit = true;
        Snackbar.make(findViewById(R.id.welcome_l), exit_msg, Snackbar.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                backPressToExit = false;
            }
        }, 2000);
    }
}
