package com.vjs.complaints.students;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class account {

    public String name, type, branch, year, hostel, complain;
    public Context mContext;

    public account(Context context) {
        this.mContext = context;
    }

    public void write() {

        String sharedPrefFile = "com.vjs.complaints";
        SharedPreferences mPreferences;
        mPreferences = mContext.getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putString("name", name);
        preferencesEditor.putString("type", type);
        preferencesEditor.putString("year", year);
        preferencesEditor.putString("branch", branch);
        preferencesEditor.putString("hostel", hostel);
        preferencesEditor.putString("complain", complain);
        preferencesEditor.apply();
        //preferencesEditor.commit();

    }


}
