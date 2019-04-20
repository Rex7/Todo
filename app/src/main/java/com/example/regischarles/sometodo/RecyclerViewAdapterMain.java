package com.example.regischarles.sometodo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapterMain extends RecyclerView.Adapter<RecyclerViewAdapterMain.RecyclerViewHolder> {
    ArrayList<Task> taskArrayList;
    Context ctx;
   public RecyclerViewAdapterMain(Context ctx,ArrayList<Task> tasks){
this.taskArrayList=tasks;
this.ctx=ctx;
   }
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview,viewGroup,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int position) {
       recyclerViewHolder.textView.setText(taskArrayList.get(position).getTask());

    }


    @Override
    public int getItemCount() {
        return taskArrayList.size();
    }
    static  class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
       TextView textView;
       Button button;

        public RecyclerViewHolder(@NonNull View itemView)
        {
            super(itemView);
            textView=itemView.findViewById(R.id.taskImp);
            button=itemView.findViewById(R.id.finished);


        }

        @Override
        public void onClick(View v) {

        }
    }
}
