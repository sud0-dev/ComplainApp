package com.vjs.complaints.students;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.vjs.complaints.BaseActivity;
import com.vjs.complaints.R;


@SuppressLint("Registered")
public class NewComplain extends BaseActivity implements Dialog.DialogListener, DefaultComplains.Pass {

    EditText name;
    Button submit, describe;
    Spinner year, hostel, complainType;
    account acc = new account(this);
    String[] string = {"","","","",""};
    //MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.branch);
        spinner.setItems(getResources().getStringArray(R.array.branch_name));
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                string[0]= item;
            }
        });

        MaterialSpinner spinner1 = (MaterialSpinner)findViewById(R.id.year);
        spinner1.setItems(getResources().getStringArray(R.array.year));
        spinner1.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                string[1] = item;
            }
        });

        MaterialSpinner spinner2 = (MaterialSpinner)findViewById(R.id.hostel);
        spinner2.setItems(getResources().getStringArray(R.array.hostel));
        spinner2.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                string[2] = item;
            }
        });

        Button complain = findViewById(R.id.complain);
        complain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DefaultComplains dialogFragment = new DefaultComplains();
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

        name = findViewById(R.id.name);

        describe = findViewById(R.id.describe);
        describe.setOnClickListener(new View.OnClickListener() {
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
                acc.branch = string[0];
                acc.year = string[1];
                acc.hostel = string[2];
                acc.write();
                //dbHandler.addHandler(acc);
                finish();
                Intent myIntent = new Intent(NewComplain.this, StudentsPage.class);
                NewComplain.this.startActivity(myIntent);
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

    @Override
    public void onDone(String type, String complain) {
        if (TextUtils.isEmpty(type) && TextUtils.isEmpty(complain)){
            acc.type = "Type";
            acc.complain = "Complain";
        }
        else{
            acc.type = type;
            acc.complain = complain;
        }
    }
}
