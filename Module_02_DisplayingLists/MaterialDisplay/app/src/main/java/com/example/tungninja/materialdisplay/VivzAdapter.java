package com.example.tungninja.materialdisplay;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by tungninja on 9/10/17.
 */

public class VivzAdapter extends RecyclerView.Adapter<VivzAdapter.MyViewHolder>{

    private LayoutInflater inflater;

    private Context context;

    List<Information> data = new ArrayList<>();

    public  VivzAdapter(Context context, List<Information> data){
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    private void delete(int position){
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.custom_row, parent, false);

        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Information current = data.get(position);

        holder.title.setText(current.title);

        holder.icon.setImageResource(current.iconId);

    }

    @Override
    public int getItemCount() {

        return data.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title;

        ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            title =  itemView.findViewById(R.id.listText);
            icon =  itemView.findViewById(R.id.listIcon);

            icon.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            delete(getPosition());
        }
    }
}
