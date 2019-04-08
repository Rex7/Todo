package com.example.regischarles.sometodo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DailogFragment extends DialogFragment {
    Button button;
    EditText inputTask;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dialog,container,false);
         button=view.findViewById(R.id.dailog_add_task);
         inputTask=view.findViewById(R.id.dialogTask);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("MyDataPlus",inputTask.getText().toString());

                getDialog().dismiss();

            }
        });
        return  view;
    }
}
