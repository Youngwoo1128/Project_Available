package com.mac_available.carrotmarket;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Tab3Fragment extends Fragment {

    ArrayList<BoastItem> items = new ArrayList<>();
    RecyclerView recyclerView;
    BoastAdapter adapter;
    Button btn;

    SwipeRefreshLayout refreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab3,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn = view.findViewById(R.id.btn);
        recyclerView = view.findViewById(R.id.recycler);
        adapter = new BoastAdapter(getActivity(), items);
        recyclerView.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BoastEditActivity.class);
                startActivity(intent);
            }
        });

        refreshLayout = view.findViewById(R.id.layout_refresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                refreshLayout.setRefreshing(false);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

        loadData();
    }

    void loadData(){
        Retrofit retrofit = RetrofitHelper.getRetrofitInstanceGson();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<ArrayList<BoastItem>> call = retrofitService.loadDataFromServer();
        call.enqueue(new Callback<ArrayList<BoastItem>>() {
            @Override
            public void onResponse(Call<ArrayList<BoastItem>> call, Response<ArrayList<BoastItem>> response) {


                items.clear();
                adapter.notifyDataSetChanged();

                ArrayList<BoastItem> list = response.body();
                for (BoastItem item : list){
                    items.add(0, item);
                    adapter.notifyItemInserted(0);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<BoastItem>> call, Throwable t) {

            }
        });
    }



}
