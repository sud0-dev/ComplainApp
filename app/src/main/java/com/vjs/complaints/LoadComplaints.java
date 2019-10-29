package com.vjs.complaints;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
//import static android.content.Context.MODE_PRIVATE;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class LoadComplaints extends AppCompatActivity {

    TextView dname, dyear, dbranch, dhostel, dcomplain, dtype, result;
    account acc = new account(this);
    MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadcomplaint);

        dname = findViewById(R.id.dname);
        dbranch = findViewById(R.id.dcomplain);
        dhostel = findViewById(R.id.dhostel);
        dcomplain = findViewById(R.id.dcomplain);
        dtype = findViewById(R.id.type);
        dyear = findViewById(R.id.dyear);
        result = findViewById(R.id.result);

        SharedPreferences sharedPreferences = getSharedPreferences("com.vjs.complaints", Context.MODE_PRIVATE);

        Toast.makeText(getApplicationContext(), dbHandler.loadHandler(), Toast.LENGTH_LONG).show();
        result.setText(dbHandler.loadHandler());

        acc.name = sharedPreferences.getString("name", "default value");
        acc.year = sharedPreferences.getString("year", "default value");
        acc.type = sharedPreferences.getString("type", "default value");
        acc.branch = sharedPreferences.getString("branch", "default value");
        acc.hostel = sharedPreferences.getString("hostel", "default value");
        acc.complain = sharedPreferences.getString("complain", "default value");

        dname.setText(acc.name);
        dyear.setText(acc.year + " year");
        dbranch.setText(acc.branch);
        dhostel.setText(acc.hostel);
        dtype.setText(acc.type);
        dcomplain.setText(acc.complain);
    }
}
