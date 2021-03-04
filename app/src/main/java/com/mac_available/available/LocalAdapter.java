package com.mac_available.available;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LocalAdapter extends RecyclerView.Adapter<LocalAdapter.VH> {

    Context context;
    ArrayList<LocalVO> items;

    public LocalAdapter(Context context, ArrayList<LocalVO> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.localrecycler_item, parent, false);
        VH vh = new VH(itemView);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        LocalVO item = items.get(position);

        holder.tvName.setText(item.name);
        holder.tvMsg.setText(item.msg);
    }



    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{

        TextView tvName, tvMsg;

        public VH(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.aaa);
            tvMsg = itemView.findViewById(R.id.bbb);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Intent intent = new Intent(context, ItemViewLocalActivity.class);
                    intent.putExtra("name", items.get(pos).name);
                    intent.putExtra("msg", items.get(pos).msg);
                    context.startActivity(intent);


                }
            });
        }
    }
}
