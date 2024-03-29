package com.vjs.complaints;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    String currentTheme = "", selectedTheme = "";
    SharedPreferences sharedPreferences, sharedPref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences("theme_pref", Context.MODE_PRIVATE);
        currentTheme = sharedPreferences.getString("current_theme", "light");
        assert currentTheme != null;
        setAppTheme(currentTheme);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        sharedPreferences = getSharedPreferences("theme_pref", Context.MODE_PRIVATE);
        selectedTheme = sharedPreferences.getString("current_theme", "light");
        if(! currentTheme.equals(selectedTheme))
            recreate();
    }

    protected void setAppTheme(String currentTheme) {
        if (currentTheme.equals("light"))
            setTheme(R.style.AppTheme);
        else
            setTheme(R.style.DarkMode);
    }
}
