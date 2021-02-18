package com.mac_available.carrotmarket;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Tab2Fragment extends Fragment {
    Button localBtn;

    RecyclerView recyclerView;
    ArrayList<LocalVO> localVO = new ArrayList<>();
    LocalAdapter adapter;

    SwipeRefreshLayout refreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler);

        adapter = new LocalAdapter(getActivity(), localVO);
        recyclerView.setAdapter(adapter);

        localBtn = view.findViewById(R.id.localBtn);

        localBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LocalUploadActivity.class);
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
        Call<ArrayList<LocalVO>> call = retrofitService.loadDataFromServer();
        call.enqueue(new Callback<ArrayList<LocalVO>>() {
            @Override
            public void onResponse(Call<ArrayList<LocalVO>> call, Response<ArrayList<LocalVO>> response) {
                localVO.clear();
                adapter.notifyDataSetChanged();
                
                ArrayList<LocalVO> list = response.body();
                for (LocalVO vo : list){
                    localVO.add(0, vo);
                    adapter.notifyItemInserted(0);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<LocalVO>> call, Throwable t) {
                Toast.makeText(getActivity(), "error"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
