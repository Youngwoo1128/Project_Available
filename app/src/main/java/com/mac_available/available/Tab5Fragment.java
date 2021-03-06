package com.mac_available.available;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Tab5Fragment extends Fragment {

    LinearLayout firstLinearLayout, secondLinearLayout;
    Button logOut;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab5,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        logOut = view.findViewById(R.id.LogOut);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);

                getActivity().finish();
            }
        });
        firstLinearLayout = view.findViewById(R.id.firstLinearLayout);
        firstLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DescriptionRegardingAvailableActivity.class);
                startActivity(intent);
            }
        });

        secondLinearLayout = view.findViewById(R.id.secondLinearLayout);
        secondLinearLayout.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(getActivity(), GoogleMapActivity.class);
              startActivity(intent);
          }
      });

    }
}
