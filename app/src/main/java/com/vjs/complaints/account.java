package com.vjs.complaints;

import android.content.Context;
import android.content.SharedPreferences;
import static android.content.Context.MODE_PRIVATE;

public class account {

    String name, type, branch, year, hostel, complaint;

    //public static SharedPreferences sharedPreferences;
    //private SharedPreferences mPreferences;
    private Context mContext;
    //private String sharedPrefFile = "com.vjs.complaints";

    public account(Context context){
        this.mContext=context;
    }

    public void write () {

        String sharedPrefFile = "com.vjs.complaints";
        SharedPreferences mPreferences;
        mPreferences = mContext.getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putString("name", name);
        preferencesEditor.putString("year", year);
        preferencesEditor.putString("type", type);
        preferencesEditor.putString("hostel", hostel);
        preferencesEditor.putString("complaint", complaint);
        //preferencesEditor.apply();
        preferencesEditor.commit();

    }


}
