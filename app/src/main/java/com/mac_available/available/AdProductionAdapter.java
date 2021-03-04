package com.mac_available.available;

//package com.mac_available.carrotmarket;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdProductionAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<ProductVO> items;

    public AdProductionAdapter(Context context, ArrayList<ProductVO> items){
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

        ProductVO item = items.get(position);

        vh.pd_name.setText(item.title);
        vh.time.setText(item.time);
        vh.location.setText(item.location);
        vh.price.setText(item.price);

        Glide.with(context).load(item.imageUri).into(vh.ivImg);

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

            itemView.setOnClickListener(new View.OnClickListener() {
//                Context context;
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();

                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference = firebaseDatabase.getReference("items");
                    databaseReference.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                        @Override
                        public void onSuccess(DataSnapshot dataSnapshot) {
                            for (DataSnapshot ds: dataSnapshot.getChildren()){
                                ProductVO productVO = ds.getValue(ProductVO.class);
                                if (productVO.time.equals(items.get(pos).time)){
                                    G.currentItem = productVO;
                                }
                            }
                            Intent intent = new Intent(context, ItemViewMainActivity.class);
                            context.startActivity(intent);

                        }
                    });

//                    intent.putExtra("name", items.get(pos).title);
//                    intent.putExtra("price", items.get(pos).price);
//                    intent.putExtra("content", items.get(pos).content);
//                    intent.putExtra("img", items.get(pos).imageUri);





                }
            });

        }
    }
}
