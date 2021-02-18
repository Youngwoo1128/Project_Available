package com.mac_available.carrotmarket;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mac_available.carrotmarket.AdProduction;
import com.mac_available.carrotmarket.Item;
import com.mac_available.carrotmarket.ProductVO;
import com.mac_available.carrotmarket.R;
import com.mac_available.carrotmarket.SearchActivity;
import com.mac_available.carrotmarket.UploadActivity;

import java.sql.Array;
import java.util.ArrayList;

public class Tab1Fragment extends Fragment {


   // ArrayList<Item> items = new ArrayList<>();
    RecyclerView recyclerView;
    AdProduction adapter;

    Button btn;

    //spinner
    Spinner spinner;
    ArrayAdapter arrayAdapter;

    View.OnClickListener listener;

    ImageView search,filter,bell, movie;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab1,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        products = new ArrayList<>();

        //더미데이터
        recyclerView = view.findViewById(R.id.recycler);
        adapter = new AdProduction(getContext(), products);
        recyclerView.setAdapter(adapter);

        //loadData();

        btn = view.findViewById(R.id.btn);

//        adapter = new AdProduction(getActivity(), items);
//        recyclerView.setAdapter(adapter);

        spinner = view.findViewById(R.id.spinner);
        arrayAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.datas, R.layout.spinner_selected);
        spinner.setAdapter(arrayAdapter);

        arrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        //아이콘 참조
        search = view.findViewById(R.id.search);
        filter = view.findViewById(R.id.filter);
        bell = view.findViewById(R.id.bell);
        movie = view.findViewById(R.id.movie);

        movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MovieActivity.class);
                startActivity(intent);
            }
        });


        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                switch (v.getTag().toString()){
                    case "search":
                        intent = new Intent(getActivity(), SearchActivity.class);
                        break;

                    case "filter":
                       intent = new Intent(getActivity(),FilterActivity.class);
                       break;

                    case "bell":
                        intent = new Intent(getActivity(),BellActivity.class);
                        break;
                }
                startActivity(intent);
            }
        };
        search.setOnClickListener(listener);
        filter.setOnClickListener(listener);
        bell.setOnClickListener(listener);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity(), ""+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // items.add(0, new Item(R.drawable.ch_chopa, "2020 Special Edition 초파인형", "성동구 * ", "7분전", "300만원"));
                //adapter.notifyItemInserted(0);
                //Toast.makeText(getActivity(), "나왔어" + items.size(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), UploadActivity.class);
                startActivity(intent);
            }
        });
    }

    //TODO 화면 돌아왔을때 리사이클러뷰 보여주고 갱신하기

    ArrayList<ProductVO> products;

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    public void loadData(){
        products.clear();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference itemRef = database.getReference("items");
        itemRef.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    ProductVO item = ds.getValue(ProductVO.class);
                    products.add(0,item);
                }

                adapter.notifyDataSetChanged();
            }
        });
    }
}
