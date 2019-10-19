package com.vjs.complaints;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ImageView;

public class StudentPage extends AppCompatActivity {

    Button create, load;
    ImageView img;

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
            }
        });

        img = findViewById(R.id.logo);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(StudentPage.this, webpage.class);
                StudentPage.this.startActivity(myIntent);
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
}
