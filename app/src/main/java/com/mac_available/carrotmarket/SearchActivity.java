package com.mac_available.carrotmarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class SearchActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;

    ViewPager viewPager;

    SearchTabAdapter searchTabAdapter;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        toolbar = findViewById(R.id.search_toolbar);
        tabLayout = findViewById(R.id.tablayout);
        viewPager=findViewById(R.id.pager);

        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fragmentManager=getSupportFragmentManager();
        searchTabAdapter = new SearchTabAdapter(fragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(searchTabAdapter);

        tabLayout.setupWithViewPager(viewPager);
    }
}