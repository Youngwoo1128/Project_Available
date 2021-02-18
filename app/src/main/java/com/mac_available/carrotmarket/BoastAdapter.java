package com.mac_available.carrotmarket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BoastAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<BoastVO> items;

    public BoastAdapter(Context context, ArrayList<BoastVO> items){
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public BoastVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.boastrecycleritem, parent, false);

        BoastVH vh = new BoastVH(itemView);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        BoastVH vh = (BoastVH) holder;
        BoastVO item = items.get(position);

        vh.tvName.setText(item.title);
        vh.tvMsg.setText(item.msg);

        Glide.with(context).load(item.imageUri).into(vh.iv);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class BoastVH extends RecyclerView.ViewHolder{

        ImageView iv;
        TextView tvName;
        TextView tvMsg;

        public BoastVH(@NonNull View itemView) {
            super(itemView);

            iv = itemView.findViewById(R.id.iv);
            tvName = itemView.findViewById(R.id.name);
            tvMsg=itemView.findViewById(R.id.msg);
        }
    }
}
