package com.vjs.complaints;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.util.Objects;

public class Dialog extends DialogFragment {

    public interface DialogListener {
        void onFinishEditDialog(String inputText);
    }

    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState){

        final EditText entry;
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        @SuppressLint("InflateParams") View DialogView = inflater.inflate(R.layout.dailogue_describe,null);
        entry = DialogView.findViewById(R.id.describe);
        Button done = DialogView.findViewById(R.id.btnDone);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogListener dialogListener = (DialogListener)getActivity();
                assert dialogListener != null;
                dialogListener.onFinishEditDialog(entry.getText().toString());
                dismiss();
            }
        });
        builder.setView(DialogView).setTitle("Describe").setMessage("Please describe your problem");
        return builder.create();
    }
}