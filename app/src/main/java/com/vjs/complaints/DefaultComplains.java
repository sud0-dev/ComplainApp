package com.vjs.complaints;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import com.jaredrummler.materialspinner.MaterialSpinner;
import java.util.Objects;

public class DefaultComplains extends DialogFragment {

    private String type = "" , complain = "";

    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        @SuppressLint("InflateParams") View DialogView = inflater.inflate(R.layout.default_complains,null);

        final MaterialSpinner spinner1 = (MaterialSpinner) DialogView.findViewById(R.id.defaultComplains);

        MaterialSpinner spinner = (MaterialSpinner) DialogView.findViewById(R.id.complainType);
        spinner.setItems(getResources().getStringArray(R.array.complaints));
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                 type = item;
                if(type.compareToIgnoreCase("water") == 0)
                    spinner1.setItems(getResources().getStringArray(R.array.water));
                else if(type.compareToIgnoreCase("room") == 0)
                    spinner1.setItems(getResources().getStringArray(R.array.room));
                else if(type.compareToIgnoreCase("food") == 0)
                    spinner1.setItems(getResources().getStringArray(R.array.food));
                else if(type.compareToIgnoreCase("electrical") == 0)
                    spinner1.setItems(getResources().getStringArray(R.array.Electricity));
            }
        });

        spinner1.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                complain = item;
            }
        });

        Button done = DialogView.findViewById(R.id.btnDone);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Pass pass = (Pass) getActivity();
                assert pass != null;
                pass.onDone(type, complain);
                dismiss();
            }
        });
        builder.setView(DialogView).setTitle("Add Complaints");
        return builder.create();
    }

    public interface Pass {
        void onDone(String type, String complain);
    }

}
