package com.mac_available.available;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ChattingAdapter extends BaseAdapter {

    Context context;
    ArrayList<ChattingVO> items;

    public ChattingAdapter(Context context, ArrayList<ChattingVO> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ChattingVO item = items.get(position);
        View itemView = null;
        LayoutInflater inflater = LayoutInflater.from(context);
        if (item.id.equals(G.myId)){
            itemView = inflater.inflate(R.layout.layout_chatting_list_my, parent, false);
        }else{
            itemView = inflater.inflate(R.layout.layout_chatting_list_your, parent, false);

        }

        TextView tvId = itemView.findViewById(R.id.myName);
        TextView tvMsg = itemView.findViewById(R.id.myMessage);
        TextView tvTime = itemView.findViewById(R.id.myTime);
        tvId.setText(item.id);
        tvMsg.setText(item.message);
        tvTime.setText(item.time);


        return itemView;
    }


}
