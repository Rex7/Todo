package com.example.regischarles.sometodo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewFinishedFrag extends RecyclerView.Adapter<RecyclerViewFinishedFrag.RecyclerViewHolder> {
    ArrayList<Task> taskArrayList;
    private Context ctx;
    DatabaseHelper helper;
    public RecyclerViewFinishedFrag(Context ctx, ArrayList<Task> tasks, DatabaseHelper helper){
        this.taskArrayList=tasks;
        this.ctx=ctx;
        this.helper=helper;
    }


    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.finished_item,viewGroup,false);
        return new RecyclerViewFinishedFrag.RecyclerViewHolder(view,taskArrayList,ctx);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int position) {
        recyclerViewHolder.textView.setText(taskArrayList.get(position).getTask());
        recyclerViewHolder.title.setText(taskArrayList.get(position).getSubject());


    }

    @Override
    public int getItemCount() {
        return taskArrayList.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView title;
        private Context mContext;
        private ArrayList<Task> tasks;

        public RecyclerViewHolder(@NonNull View itemView,ArrayList<Task> taskArrayList, Context ctx) {
            super(itemView);
            this.mContext=mContext;
            this.tasks=tasks;
            textView=itemView.findViewById(R.id.taskImp_finised);
            title=itemView.findViewById(R.id.title_finised);
        }



    }
}
