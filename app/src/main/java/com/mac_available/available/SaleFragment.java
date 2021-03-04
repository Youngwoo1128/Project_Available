package com.mac_available.available;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SaleFragment extends Fragment{

    RecyclerView gridRecycler;
    ArrayList<Integer> categoryItems;
    GridRecyclerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sale, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gridRecycler = view.findViewById(R.id.gridrecycler);
        categoryItems = new ArrayList<>();
        addCategoryItems();

        adapter = new GridRecyclerAdapter(getActivity(), categoryItems);
        gridRecycler.setAdapter(adapter);

    }

    public void addCategoryItems(){

        categoryItems.add(R.drawable.c_digital);
        categoryItems.add(R.drawable.c_interior);
        categoryItems.add(R.drawable.c_kids);
        categoryItems.add(R.drawable.c_sports);
        categoryItems.add(R.drawable.c_w_merchandise);
        categoryItems.add(R.drawable.c_w_clothes);
        categoryItems.add(R.drawable.c_men);
        categoryItems.add(R.drawable.c_game);
        categoryItems.add(R.drawable.c_cosmetic);
        categoryItems.add(R.drawable.c_pet);
        categoryItems.add(R.drawable.c_ticket);
        categoryItems.add(R.drawable.c_plant);
    }
}
