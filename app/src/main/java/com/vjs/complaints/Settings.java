package com.vjs.complaints;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;

import com.vjs.complaints.students.StudentsPage;

import java.util.Set;

public class Settings extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        CompoundButton mintTheme = findViewById(R.id.mintTheme);

        sharedPreferences = getSharedPreferences("theme_pref", Context.MODE_PRIVATE);
        String currentTheme = sharedPreferences.getString("current_theme", "light");

        final SharedPreferences mPreferences;
        mPreferences = getSharedPreferences("theme_pref", MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits") final SharedPreferences.Editor preferencesEditor = mPreferences.edit();

        assert currentTheme != null;
        if(currentTheme.equals("light")){
            mintTheme.setChecked(false);
        } else {
            mintTheme.setChecked(true);
        }

        mintTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    preferencesEditor.putString("current_theme", "dark").apply();
                } else {
                    preferencesEditor.putString("current_theme", "light").apply();
                }
                recreate();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Settings.this, StudentsPage.class);
        Settings.this.startActivity(intent);
    }

}
