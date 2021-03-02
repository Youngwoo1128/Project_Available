package com.mac_available.carrotmarket;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChattingListAdapter extends RecyclerView.Adapter<ChattingListAdapter.VH> {

    Context context;
    ArrayList<String> items;
    ArrayList<Integer> serverNum;

    public ChattingListAdapter(Context context, ArrayList<String> items, ArrayList<Integer> serverNum) {
        this.context = context;
        this.items = items;
        this.serverNum = serverNum;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(context).inflate(R.layout.layout_chatting_list_recycler_item, parent ,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.tvChattingListId.setText(items.get(position) + "님 과의 대화방");
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{

        TextView tvChattingListId;

        public VH(@NonNull View itemView) {
            super(itemView);
            tvChattingListId = itemView.findViewById(R.id.tv_chatting_list_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ChattingActivity.class);
                    int pos = getAdapterPosition();
                    if (serverNum.get(pos) == 1){
                        intent.putExtra("server", G.myId + "&&" + items.get(pos));
                        Toast.makeText(context, "if o", Toast.LENGTH_SHORT).show();
                    } else {
                        intent.putExtra("server", items.get(pos) + "&&" + G.myId);
                        Toast.makeText(context, "if x", Toast.LENGTH_SHORT).show();
                    }

                    context.startActivity(intent);

                }
            });
        }
    }
}
