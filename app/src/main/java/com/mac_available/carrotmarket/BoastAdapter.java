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

public class BoastAdapter extends RecyclerView.Adapter<BoastAdapter.VH> {

    Context context;
    ArrayList<BoastItem> items;

    public BoastAdapter(Context context, ArrayList<BoastItem> items){
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.boastrecycleritem, parent, false);
        VH vh = new VH(itemView);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

        BoastItem item = items.get(position);

        String imgUri = "http://vlakd3210000.dothome.co.kr/Retrofit"+item.file;
        Glide.with(context).load(imgUri).into(holder.iv);

        holder.tvName.setText(item.title);
        holder.tvMsg.setText(item.msg);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{

        ImageView iv;
        TextView tvName;
        TextView tvMsg;

        public VH(@NonNull View itemView) {
            super(itemView);

            iv = itemView.findViewById(R.id.iv);
            tvName = itemView.findViewById(R.id.name);
            tvMsg=itemView.findViewById(R.id.msg);
        }
    }
}
