package com.vjs.complaints;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import java.util.Objects;

public class ErrorDialog extends AppCompatDialogFragment {

    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        @SuppressLint("InflateParams") View v = inflater.inflate(R.layout.activity_errordialog, null);

        Bundle bundle = getArguments();
        assert bundle != null;
        String userName = bundle.getString("TEXT", "");
        String str = "Did you forget the password for " + userName + " ?";

        TextView textView = v.findViewById(R.id.error);
        textView.setText(str);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v)
                .setTitle("")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        return builder.create();
    }
}