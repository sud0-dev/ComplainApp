package com.vjs.complaints;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button login;
    EditText username, password;
    TextView text;
    String user, pass;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int Permission_All = 1;

        String[] Permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.INTERNET, };

        if(!hasPermissions(this, Permissions)){
            ActivityCompat.requestPermissions(this, Permissions, Permission_All);
        }


        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        text = findViewById(R.id.text);
        img = findViewById(R.id.logo);

        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user = username.getText().toString();
                pass = password.getText().toString();

                if (user.equals("admin") && pass.equals("admin")) {
                    Intent myIntent = new Intent(MainActivity.this, AdminPage.class);
                    MainActivity.this.startActivity(myIntent);
                }

                else if (user.equals("student") && pass.equals("student")) {
                    Intent myIntent = new Intent(MainActivity.this, StudentPage.class);
                    MainActivity.this.startActivity(myIntent);
                }

                else
                    text.setText("Wrong Username or Password");
            }
        });

        img = findViewById(R.id.logo);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, webpage.class);
                MainActivity.this.startActivity(myIntent);
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

}
