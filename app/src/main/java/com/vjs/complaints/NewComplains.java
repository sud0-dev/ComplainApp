package com.vjs.complaints;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

public class NewComplains extends Fragment implements Dialog.DialogListener {

    private EditText name;
    private Spinner branch, year, hostel, complainType;
    private String string;
    //MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_create, container ,false);

        name = rootView.findViewById(R.id.name);
        branch = rootView.findViewById(R.id.branch);
        year = rootView.findViewById(R.id.year);
        hostel = rootView.findViewById(R.id.hostel);
        complainType = rootView.findViewById(R.id.complainttype);
        final account acc = new account(getActivity());

        Button describe = rootView.findViewById(R.id.describe);
        describe.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                Dialog dialogFragment = new Dialog();
                Bundle bundle = new Bundle();
                bundle.putBoolean("notAlertDialog", true);
                dialogFragment.setArguments(bundle);
                assert getFragmentManager() != null;
                FragmentTransaction ft = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("dialog");
                dialogFragment.setTargetFragment(getParentFragment(), 0);
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                dialogFragment.show(ft, "dialog");
            }
        });

        Button submit = rootView.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                acc.name = name.getText().toString();
                acc.branch = branch.getSelectedItem().toString();
                acc.hostel = hostel.getSelectedItem().toString();
                acc.type = complainType.getSelectedItem().toString();
                acc.year = year.getSelectedItem().toString();
                acc.complain = string;
                acc.write();
                //dbHandler.addHandler(acc);
                Toast.makeText(getActivity(), "Complain Submitted", Toast.LENGTH_LONG).show();
            }
        });
        return rootView;
    }

    @Override
    public void onFinishEditDialog(String inputText) {
        if (TextUtils.isEmpty(inputText)) {
            string = "No Complains";
        } else
            string = inputText;
    }
}

