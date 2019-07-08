package com.example.regischarles.sometodo;

import android.annotation.SuppressLint;
import android.content.Context;
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

@SuppressLint("ValidFragment")
public class DailogFragment extends DialogFragment {
    Button button;
    EditText inputTask;
    Context context;
    SessionManage sessionManage;
@SuppressLint("ValidFragment")
public DailogFragment(Context context,SessionManage sessionManage){
Log.v("MyDataPlus","hellobabe");
this.sessionManage=sessionManage;
this.context=context;
}
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
                DatabaseHelper helper=new DatabaseHelper(context,null,null,4);
                Task task=new Task();
                task.setTask(inputTask.getText().toString().trim());
                task.setStatus("added");
                task.setUsername(sessionManage.getUsername());
              long count=  helper.addTask(task);

              Log.v("MyDataPlus","Count "+count);
         todoFrag frag= (todoFrag) getFragmentManager().getFragments().get(0);
                frag.refreshData();



                getDialog().dismiss();

            }
        });
        return  view;
    }
}
