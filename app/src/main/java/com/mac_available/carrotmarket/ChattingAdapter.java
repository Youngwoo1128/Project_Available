package com.mac_available.carrotmarket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

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

//        //현재 보여줄 번째 (position)의 데이터 얻어오기
//        MessageItem item = messageItems.get(position);
//
//        //재활용할 뷰[converterView]는 사용하지 않을 것임
//        View itemView = null;
//
//        //메세지가 내 메세지 인지..
//        LayoutInflater inflater = LayoutInflater.from(context);
//
//        if (item.name.equals(G.nickName) ){
//            itemView = inflater.inflate(R.layout.my_messagebox, parent, false);
//        }else{
//            itemView = inflater.inflate(R.layout.other_messagebox, parent, false);
//        }
//
//        //bind view : 값 연결
//        CircleImageView civ = itemView.findViewById(R.id.iv);
//        TextView tvName = itemView.findViewById(R.id.tv_name);
//        TextView tvMsg = itemView.findViewById(R.id.tv_msg);
//        TextView tvTime = itemView.findViewById(R.id.tv_time);
//
//        tvName.setText(item.name);
//        tvMsg.setText(item.message);
//        tvTime.setText(item.time);
//
//        Glide.with(context).load(item.profileUrl).into(civ);
//
//        return itemView;


        return itemView;
    }


}
