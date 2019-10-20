package com.vjs.complaints;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Create extends AppCompatActivity implements Dialog.DialogListener {

    EditText name;
    Button submit, describe;
    Spinner branch, year, hostel, complainType;
    account acc = new account(this);
    MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        name = findViewById(R.id.name);
        branch = findViewById(R.id.branch);
        year = findViewById(R.id.year);
        hostel = findViewById(R.id.hostel);
        complainType = findViewById(R.id.complainttype);

        describe = findViewById(R.id.describe);
        describe.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                Dialog dialogFragment = new Dialog();
                Bundle bundle = new Bundle();
                bundle.putBoolean("notAlertDialog", true);
                dialogFragment.setArguments(bundle);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                dialogFragment.show(ft, "dialog");
            }
        });

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                acc.name = name.getText().toString();
                acc.branch = branch.getSelectedItem().toString();
                acc.hostel = hostel.getSelectedItem().toString();
                acc.type = complainType.getSelectedItem().toString();
                acc.year = year.getSelectedItem().toString();
                acc.write();
                dbHandler.addHandler(acc);
                Intent myIntent = new Intent(Create.this, StudentPage.class);
                Create.this.startActivity(myIntent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                Toast.makeText(getApplicationContext(), "Complain Submitted", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onFinishEditDialog(String inputText) {

        if (TextUtils.isEmpty(inputText)) {
            acc.complain = "No Complains";
        } else
            acc.complain = inputText;
    }

}
