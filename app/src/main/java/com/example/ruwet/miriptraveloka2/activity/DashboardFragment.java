package com.example.ruwet.miriptraveloka2.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ruwet.miriptraveloka2.model.DaftarRuangan;

//import com.example.ruwet.miriptraveloka2.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {


    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_dashboard, container, false);
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        Button btn = (Button) rootView.findViewById(R.id.btnbook);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Booking.class);
                startActivity(intent);
            }
        });
        return rootView;
    }



}
