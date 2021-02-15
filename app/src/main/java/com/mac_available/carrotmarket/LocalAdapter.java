package com.mac_available.carrotmarket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LocalAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<LocalVO> items;

    public LocalAdapter(Context context, ArrayList<LocalVO> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(context).inflate(R.layout.localrecycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        VH vh = (VH) holder;

       LocalVO item = items.get(position);
       vh.tv1.setText(item.nick);
       vh.tv2.setText(item.msg);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{

        TextView tv1, tv2;


        public VH(@NonNull View itemView) {
            super(itemView);

            tv1 = itemView.findViewById(R.id.aaa);
            tv2 = itemView.findViewById(R.id.bbb);
        }
    }
}
