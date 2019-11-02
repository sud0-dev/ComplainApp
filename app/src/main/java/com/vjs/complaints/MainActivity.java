package com.vjs.complaints;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    Button login, theme;
    EditText username, password;
    TextView text, Link;
    String user, pass;
    private boolean backPressToExit = false;
    String exit_msg = "Press back again to exit";
    int Permission_All = 1;
    int CHECK_NIGHT_MODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            setTheme(R.style.DarkMode);
        else
            setTheme(R.style.AppTheme);

        String[] Permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.INTERNET, };

        if(!hasPermissions(this, Permissions)){
            ActivityCompat.requestPermissions(this, Permissions, Permission_All);
        }

        login = findViewById(R.id.login);
        login.setEnabled(false);
        login.setBackgroundTintList((ContextCompat.getColorStateList(MainActivity.this, R.color.disabled)));

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        text = findViewById(R.id.text);

        password.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                login.setEnabled(false);
                login.setBackgroundTintList((ContextCompat.getColorStateList(MainActivity.this, R.color.disabled)));
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                login.setBackgroundTintList((ContextCompat.getColorStateList(MainActivity.this, R.color.button)));
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(! password.getText().toString().isEmpty())
                    login.setEnabled(true);
                else {
                    login.setEnabled(false);
                    login.setBackgroundTintList((ContextCompat.getColorStateList(MainActivity.this, R.color.disabled)));
                }
            }
        });

        Button student = findViewById(R.id.student);
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, StudentsPage.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

        Button admin = findViewById(R.id.admin);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, AdminPage.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user = username.getText().toString();
                pass = password.getText().toString();

                if (user.equals("admin") && pass.equals("admin")) {
                    Intent myIntent = new Intent(MainActivity.this, AdminPage.class);
                    MainActivity.this.startActivity(myIntent);
                    finish();
                } else if (user.equals("student") && pass.equals("student")) {
                    Intent myIntent = new Intent(MainActivity.this, StudentsPage.class);
                    MainActivity.this.startActivity(myIntent);
                    finish();
                } else {
                    ErrorDialog dialogFragment = new ErrorDialog();
                    Bundle bundle = new Bundle();
                    bundle.putString("TEXT", user);
                    dialogFragment.setArguments(bundle);
                    dialogFragment.show(getSupportFragmentManager(), "Image Dialog");
                }
            }
        });

        Link = findViewById(R.id.site);
        Link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, webpage.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

        theme = findViewById(R.id.theme);
        theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CHECK_NIGHT_MODE == 1) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    CHECK_NIGHT_MODE = 0;
                    theme.setText("Light mode");
                    restartApp();
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    CHECK_NIGHT_MODE = 1;
                    theme.setText("Dark mode");
                    restartApp();
                }
            }
        });
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

    public void restartApp () {
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        MainActivity.this.startActivity(myIntent);
    }

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
