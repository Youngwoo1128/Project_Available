package com.mac_available.carrotmarket;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LocationSearchAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<String> locationItems;

    public LocationSearchAdapter(Context context, ArrayList<String> locationItems) {
        this.context = context;
        this.locationItems = locationItems;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(context).inflate(R.layout.location_result_recycler_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((VH)holder).tv.setText(locationItems.get(position));
    }

    @Override
    public int getItemCount() {
        return locationItems.size();
    }

    class VH extends RecyclerView.ViewHolder{

        TextView tv;

        public VH(@NonNull View itemView) {
            super(itemView);

            tv = itemView.findViewById(R.id.tv_location_result);
            final Intent intent = ((LocationSearchActivity)context).getIntent();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    String address = locationItems.get(pos);
                    intent.putExtra("location", address);
                    ((LocationSearchActivity)context).setResult(Activity.RESULT_OK, intent);
                    ((LocationSearchActivity) context).finish();
                }
            });
        }
    }
}
