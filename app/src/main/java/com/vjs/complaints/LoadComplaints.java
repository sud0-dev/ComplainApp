package com.vjs.complaints;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
//import static android.content.Context.MODE_PRIVATE;

import android.os.Bundle;
import android.widget.TextView;

public class LoadComplaints extends AppCompatActivity {

    TextView dname,dyear, dcollege, dlocation, dbranch;
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor = sharedPreferences.edit();
    account acc = new account(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadcomplaint);

        dname = findViewById(R.id.dname);
        dbranch = findViewById(R.id.dbranch);
        dlocation = findViewById(R.id.dlocation);
        dcollege = findViewById(R.id.dcollege);
        dyear = findViewById(R.id.dyear);

//        SharedPreferences sharedPreferences = getSharedPreferences("com.decodebros.complaints", Context.MODE_PRIVATE);
//
//        acc.name = sharedPreferences.getString("name", "default value");
//        acc.college = sharedPreferences.getString("college", "default value");
//        acc.department = sharedPreferences.getString("department", "default value");
//        acc.year = sharedPreferences.getString("year", "default value");
//        acc.location = sharedPreferences.getString("location", "default value");
//
//        dname.setText(acc.name);
//        dyear.setText(acc.year + " year");
//        dcollege.setText(acc.college);
//        dlocation.setText(acc.location);
//        dbranch.setText(acc.department);



    }
}
