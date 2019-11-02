package com.vjs.complaints;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.Objects;

public class LoadComplains extends Fragment {

    //MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

    @SuppressLint("CutPasteId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_loadcomplaint, container ,false);

        TextView dname = rootView.findViewById(R.id.dname);
        TextView dbranch = rootView.findViewById(R.id.dbranch);
        TextView dhostel = rootView.findViewById(R.id.dhostel);
        TextView dcomplain = rootView.findViewById(R.id.dcomplain);
        TextView dtype = rootView.findViewById(R.id.type);
        TextView dyear = rootView.findViewById(R.id.dyear);
        TextView result = rootView.findViewById(R.id.result);

        account acc = new account(getActivity());

        SharedPreferences sharedPreferences = Objects.requireNonNull(this.getActivity()).getSharedPreferences("com.vjs.complaints", Context.MODE_PRIVATE);
//        Toast.makeText(getActivity(), dbHandler.loadHandler(), Toast.LENGTH_LONG).show();
//        result.setText(dbHandler.loadHandler());

        acc.name = sharedPreferences.getString("name", "default value");
        acc.year = sharedPreferences.getString("year", "default value");
        acc.type = sharedPreferences.getString("type", "default value");
        acc.branch = sharedPreferences.getString("branch", "default value");
        acc.hostel = sharedPreferences.getString("hostel", "default value");
        acc.complain = sharedPreferences.getString("complain", "default value");

        dname.setText(acc.name);
        dyear.setText(acc.year + " year");
        dbranch.setText(acc.branch);
        dhostel.setText(acc.hostel);
        dtype.setText(acc.type);
        dcomplain.setText(acc.complain);

        return rootView;
    }

}
