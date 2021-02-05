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

public class AdProduction extends RecyclerView.Adapter {

    Context context;
    ArrayList<Item> items;

    public AdProduction(Context context, ArrayList<Item> items){
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.recycler_item, parent, false);
        VH holder = new VH(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH)holder;

        Item item = items.get(position);
        vh.pd_name.setText(item.name);
        vh.location.setText(item.location);
        vh.time.setText(item.time);
        vh.price.setText(item.price);

        Glide.with(context).load(item.img).into(vh.ivImg);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //아이템뷰 안에 있는 뷰들의 참조변수를 멤버로 가지는 클래스
    class VH extends RecyclerView.ViewHolder{

        ImageView ivImg;
        TextView pd_name;
        TextView location;
        TextView time;
        TextView price;

        public VH(@NonNull View itemView) {
            super(itemView);

            ivImg = itemView.findViewById(R.id.img);
            pd_name = itemView.findViewById(R.id.production_name);
            location = itemView.findViewById(R.id.location);
            time = itemView.findViewById(R.id.released_time);
            price = itemView.findViewById(R.id.price);

        }
    }
}
