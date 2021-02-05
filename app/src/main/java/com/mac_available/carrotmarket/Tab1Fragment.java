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

import java.sql.Array;
import java.util.ArrayList;

public class Tab1Fragment extends Fragment {


    ArrayList<Item> items = new ArrayList<>();
    RecyclerView recyclerView;
    AdProduction adapter;

    Button btn;

    //spinner
    Spinner spinner;
    ArrayAdapter arrayAdapter;

    View.OnClickListener listener;

    ImageView search,filter,bell;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab1,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //더미데이터
        recyclerView = view.findViewById(R.id.recycler);
        btn = view.findViewById(R.id.btn);

        adapter = new AdProduction(getActivity(), items);
        recyclerView.setAdapter(adapter);

        spinner = view.findViewById(R.id.spinner);
        arrayAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.datas, R.layout.spinner_selected);
        spinner.setAdapter(arrayAdapter);

        arrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        //아이콘 참조
        search = view.findViewById(R.id.search);
        filter = view.findViewById(R.id.filter);
        bell = view.findViewById(R.id.bell);






        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                switch (v.getTag().toString()){
                    case "search":
                        intent = new Intent(getActivity(),SearchActivity.class);
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
                items.add(0, new Item(R.drawable.ch_chopa, "2020 Special Edition 초파인형", "성동구 * ", "7분전", "300만원"));
                adapter.notifyItemInserted(0);
                //Toast.makeText(getActivity(), "나왔어" + items.size(), Toast.LENGTH_SHORT).show();
            }
        });
    }



}
