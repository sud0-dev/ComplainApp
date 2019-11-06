package com.vjs.complaints;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.snackbar.Snackbar;

import com.vjs.complaints.admin.AdminPage;
import com.vjs.complaints.students.StudentsPage;

public class login extends AppCompatActivity {

    ImageView login;
    EditText username, password;
    TextView text, Link, signUp;
    String user, pass;
    RadioButton remember;
    private boolean backPressToExit = false;
    String exit_msg = "Press back again to exit";
    int Permission_All = 1;
    //int CHECK_NIGHT_MODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String[] Permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.INTERNET, };

        if(!hasPermissions(this, Permissions)){
            ActivityCompat.requestPermissions(this, Permissions, Permission_All);
        }

        login = findViewById(R.id.login);
        login.setEnabled(false);
        login.setImageResource(R.drawable.ldisabled);
        //login.setBackgroundTintList((ContextCompat.getColorStateList(com.vjs.complaints.login.this, R.color.disabled)));

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        text = findViewById(R.id.text);

        password.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                login.setEnabled(false);
                login.setImageResource(R.drawable.ldisabled);
                //login.setBackgroundTintList((ContextCompat.getColorStateList(com.vjs.complaints.login.this, R.color.disabled)));
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                login.setImageResource(R.drawable.login);
                //login.setBackgroundTintList((ContextCompat.getColorStateList(com.vjs.complaints.login.this, R.color.button)));
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(! password.getText().toString().isEmpty())
                    login.setEnabled(true);
                else {
                    login.setEnabled(false);
                    login.setImageResource(R.drawable.ldisabled);
                    //login.setBackgroundTintList((ContextCompat.getColorStateList(com.vjs.complaints.login.this, R.color.disabled)));
                }
            }
        });

        signUp = findViewById(R.id.signUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.vjs.complaints.login.this, signup.class);
                com.vjs.complaints.login.this.startActivity(intent);
            }
        });

        remember = findViewById(R.id.radioButton);
//        Button student = findViewById(R.id.student);
//        student.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent myIntent = new Intent(com.vjs.complaints.login.this, StudentsPage.class);
//                com.vjs.complaints.login.this.startActivity(myIntent);
//                finish();
//            }
//        });
//
//        Button admin = findViewById(R.id.admin);
//        admin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent myIntent = new Intent(com.vjs.complaints.login.this, AdminPage.class);
//                com.vjs.complaints.login.this.startActivity(myIntent);
//                finish();
//            }
//        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user = username.getText().toString();
                pass = password.getText().toString();

                if (user.equals("admin") && pass.equals("admin")) {
                    Intent myIntent = new Intent(com.vjs.complaints.login.this, AdminPage.class);
                    com.vjs.complaints.login.this.startActivity(myIntent);
                    finish();
                } else if (user.equals("student") && pass.equals("student")) {
                    if(remember.isChecked()){
                        String sharedPrefFile = "user.details";
                        SharedPreferences mPreferences;
                        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
                        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                        preferencesEditor.putString("user", username.getText().toString());
                        preferencesEditor.putString("pass", password.getText().toString());
                        preferencesEditor.apply();
                        Intent myIntent = new Intent(com.vjs.complaints.login.this, StudentsPage.class);
                        com.vjs.complaints.login.this.startActivity(myIntent);
                        finish();
                    }
                } else {
                    ErrorDialog dialogFragment = new ErrorDialog();
                    Bundle bundle = new Bundle();
                    bundle.putString("TEXT", user);
                    dialogFragment.setArguments(bundle);
                    dialogFragment.show(getSupportFragmentManager(), "Image Dialog");
                }
            }
        });

//        Link = findViewById(R.id.site);
//        Link.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent myIntent = new Intent(com.vjs.complaints.login.this, webpage.class);
//                com.vjs.complaints.login.this.startActivity(myIntent);
//            }
//        });
    }

    public boolean hasPermissions(Context context, String... permissions){
        if(context!=null && permissions!=null){
            for(String permission: permissions){
                if(ActivityCompat.checkSelfPermission(context, permission)!=PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getApplicationContext(),"Permission DENIED", Toast.LENGTH_LONG).show();
                    return  false;
                }
            }
        }
        return true;
    }

//    public void restartApp () {
//        Intent myIntent = new Intent(getApplicationContext(), com.vjs.complaints.login.class);
//        com.vjs.complaints.login.this.startActivity(myIntent);
//    }

    @Override
    public void onBackPressed() {
        if (backPressToExit) {
            super.onBackPressed();
            return;
        }
        this.backPressToExit = true;
        Snackbar.make(findViewById(R.id.main_l), exit_msg, Snackbar.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                backPressToExit = false;
            }
        }, 2000);
    }
}
