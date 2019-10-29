package com.vjs.complaints;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AdminPage extends AppCompatActivity {

    TextView name, year, branch, hostel, type, complain;
    account acc = new account(this);
    String com;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpage);

        name = findViewById(R.id.a_name);
        year = findViewById(R.id.a_year);
        branch = findViewById(R.id.a_branch);
        hostel = findViewById(R.id.a_hostel);
        type = findViewById(R.id.a_type);
        complain = findViewById(R.id.a_complain);

        SharedPreferences sharedPreferences = getSharedPreferences("com.vjs.complaints", Context.MODE_PRIVATE);

        acc.name = sharedPreferences.getString("name", "default value");
        acc.year = sharedPreferences.getString("year", "default value");
        acc.type = sharedPreferences.getString("type", "default value");
        acc.branch = sharedPreferences.getString("branch", "default value");
        acc.hostel = sharedPreferences.getString("hostel", "default value");
        acc.complain = sharedPreferences.getString("complain", "default value");
        com = acc.complain;

        name.setText(acc.name);
        year.setText(acc.year + " year");
        branch.setText(acc.branch);
        hostel.setText(acc.hostel);
        type.setText(acc.type);
        complain.setText(acc.complain);

        complain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

    }

    public void openDialog() {
        ComplainDialog dialogFragment = new ComplainDialog();
        Bundle bundle = new Bundle();
        bundle.putString("TEXT",com);
        dialogFragment.setArguments(bundle);
        dialogFragment.show(getSupportFragmentManager(),"Image Dialog");
    }
}
