package com.vjs.complaints;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class Create extends AppCompatActivity {

    EditText name;
    Button submit;
    Spinner branch, year, hostel, complainType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        name = findViewById(R.id.name);
        branch = findViewById(R.id.branch);
        year = findViewById(R.id.year);
        final account acc = new account(this);


        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                acc.name = name.getText().toString();
                acc.branch = branch.getSelectedItem().toString();
                acc.hostel = hostel.getSelectedItem().toString();
                acc.type = complainType.getSelectedItem().toString();
                acc.year = year.getSelectedItem().toString();
                acc.write();
            }
        });

        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }
}
