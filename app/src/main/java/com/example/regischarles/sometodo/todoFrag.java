package com.example.regischarles.sometodo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class todoFrag extends Fragment {
    Context context;
    RecyclerView recyclerView;
    SessionManage sessionManage;
    RecyclerViewAdapterMain recyclerViewAdapterMain;
    ArrayList<Task> getAllTask;

    @SuppressLint("ValidFragment")
    public todoFrag(Context context){
        this.context=context;
    }
    Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sessionManage= new SessionManage(context);

        View view= inflater.inflate(R.layout.fragment_todo,container,false);

        recyclerView=view.findViewById(R.id.recyclerViewTodo);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setClipToPadding(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        DatabaseHelper helper=new DatabaseHelper(context,null,null,3);
        getAllTask= helper.getAllRecord(sessionManage);
       recyclerViewAdapterMain =new RecyclerViewAdapterMain(context,getAllTask);
      recyclerView.setAdapter(recyclerViewAdapterMain);








        return  view;
    }
}
