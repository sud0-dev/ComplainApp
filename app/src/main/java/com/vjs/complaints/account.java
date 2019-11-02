package com.vjs.complaints;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

class account {

    String name, type, branch, year, hostel, complain;
    private Context mContext;

    account(Context context) {
        this.mContext = context;
    }

    void write() {

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
