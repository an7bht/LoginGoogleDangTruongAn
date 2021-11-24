package com.example.logingoogledangtruongan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NhacAdapter extends RecyclerView.Adapter<NhacAdapter.ThingViewHolder> {

    OnClickListener listener;
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Nhac> nhacs;

    public NhacAdapter(Context context, ArrayList<Nhac> nhacs, OnClickListener listener) {
        layoutInflater= LayoutInflater.from(context);
        this.nhacs = nhacs;
        this.listener =listener;
    }

    public NhacAdapter(ArrayList<Nhac>arrayList){
        this.nhacs = arrayList;
    }
    @NonNull
    @Override
    public ThingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView= layoutInflater.inflate(R.layout.item_recycleviewview, parent, false);
        return new ThingViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ThingViewHolder holder, int position) {

        Nhac nhac = nhacs.get(position);
        holder.nhac =nhacs.get(position);
        holder.tvLove.setText(nhac.getLove());
        holder.tvTayLoy.setText(nhac.getTayloy());
        //holder.viewReCy.setId(recyclerViewActivity.getViewrecycle());
        holder.imgTayLoy.setImageResource(nhac.getImgtayloy());

        holder.nhac=nhacs.get(position);


    }

    @Override
    public int getItemCount() {

        return nhacs.size();
    }

    public class ThingViewHolder extends  RecyclerView.ViewHolder{
        Nhac nhac;
        TextView tvTayLoy, tvLove;
        //View viewReCy;
        ImageView imgTayLoy;
        NhacAdapter nhacAdapter;
        public ThingViewHolder(@NonNull View itemView, NhacAdapter adapter) {
            super(itemView);

            tvLove = itemView.findViewById(R.id.txtLove);
            tvTayLoy = itemView.findViewById(R.id.txtTayLoy);
            //viewReCy = itemView.findViewById(R.id.viewRecycle);
            imgTayLoy= itemView.findViewById(R.id.imgTayLoy);

            this.nhacAdapter=adapter;
         itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.itemClick(getAdapterPosition(),imgTayLoy, tvTayLoy,tvLove);
            }
        });

    }
    }
}
