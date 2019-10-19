package com.vjs.complaints;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button login;
    EditText username, password;
    TextView text;
    String user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        text = findViewById(R.id.text);

        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user = username.getText().toString();
                pass = password.getText().toString();

                if (user.equals("admin") && pass.equals("admin")) {
                    Intent myIntent = new Intent(MainActivity.this, StudentPage.class);
                    MainActivity.this.startActivity(myIntent);
                }

                else
                    text.setText("Wrong Username or Password !");
            }
        });

    }
}
