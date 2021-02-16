package com.mac_available.carrotmarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.ArrayList;

public class LocationSearchActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<String> locationItems;
    String[] locationData;
    EditText locationEditText;
    LocationSearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_search);

        toolbar = findViewById(R.id.locationSearchToolbar);
        recyclerView = findViewById(R.id.locationSearchRecycler);
        locationEditText = findViewById(R.id.editText);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        locationData = getResources().getStringArray(R.array.location);

        locationItems = new ArrayList<>();
        adapter = new LocationSearchAdapter(this, locationItems);
        recyclerView.setAdapter(adapter);

        locationEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                locationItems.clear();
                for (int i =0; i<locationData.length; i++){
                    if (locationData[i].contains(s)){
                        locationItems.add(locationData[i]);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }


        return super.onOptionsItemSelected(item);


    }
}