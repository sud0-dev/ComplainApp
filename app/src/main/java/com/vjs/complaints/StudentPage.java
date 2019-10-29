package com.vjs.complaints;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.material.snackbar.Snackbar;

public class StudentPage extends AppCompatActivity {

    Button create, load;
    private boolean backPressToExit = false;
    String exit_msg = "Press back again to exit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentpage);

        create = findViewById(R.id.create);
        create.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(StudentPage.this, Create.class);
                StudentPage.this.startActivity(myIntent);
                SharedPreferences preferences = getSharedPreferences("com.vjs.complaints", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                //finish();
            }
        });

        load = findViewById(R.id.load);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(StudentPage.this, LoadComplaints.class);
                StudentPage.this.startActivity(myIntent);
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
        Snackbar.make(findViewById(R.id.layout), exit_msg, Snackbar.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                backPressToExit = false;
            }
        }, 2000);
    }

}
