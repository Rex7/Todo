package com.example.regischarles.sometodo;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerViewAdapterMain extends RecyclerView.Adapter<RecyclerViewAdapterMain.RecyclerViewHolder> {
    ArrayList<Task> taskArrayList;
   private Context ctx;

   public RecyclerViewAdapterMain(Context ctx,ArrayList<Task> tasks){
this.taskArrayList=tasks;
this.ctx=ctx;
   }
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview,viewGroup,false);
        return new RecyclerViewHolder(view,taskArrayList,ctx);
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
    static  class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
       TextView textView;
       TextView title;
       Button button;
       private Context mContext;
       private ArrayList<Task> tasks;

        public RecyclerViewHolder(@NonNull View itemView,ArrayList<Task> tasks,Context mContext)
        {
            super(itemView);
            textView=itemView.findViewById(R.id.taskImp);
            title=itemView.findViewById(R.id.title_todo);
            button=itemView.findViewById(R.id.finished);
            button.setOnClickListener(this);
            this.mContext=mContext;
            this.tasks=tasks;


        }

        @Override
        public void onClick(View v) {
            Log.v("UniqueTag","Clciked"+getAdapterPosition());
            Log.v("UniqueTag ","data"+tasks.get(getAdapterPosition()).getTask());
            mContext.startActivity(new Intent(mContext,Report.class));




        }
    }
}
