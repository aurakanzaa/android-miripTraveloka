package com.example.ruwet.miriptraveloka2.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruwet.miriptraveloka2.adapter.MyAdapter;
import com.example.ruwet.miriptraveloka2.model.DaftarRuangan;
import com.example.ruwet.miriptraveloka2.sql.databaseHelper;
import com.example.ruwet.miriptraveloka2.activity.R;
import com.example.ruwet.miriptraveloka2.activity.detailRuangan;
import com.example.ruwet.miriptraveloka2.adapter.MyAdapter;
import com.example.ruwet.miriptraveloka2.adapter.RecyclerItemClickListener;
import com.example.ruwet.miriptraveloka2.model.DaftarRuangan;
import com.example.ruwet.miriptraveloka2.sql.databaseHelper;



import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private MyAdapter adapter;
    private databaseHelper dbHandler;
    private TextView txt_resultadapter;
    private ImageView gambar;
    SearchView sv;
    private List<DaftarRuangan> ruanganList = new ArrayList<>();

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    public HomeFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        Button btn = (Button) rootView.findViewById(R.id.btnmap);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
            }
        });


        sv= (SearchView) rootView.findViewById(R.id.mSearch);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.myRecycler);

        //SET ITS PROPETRIES
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        //ADAPTER
        dbHandler = new databaseHelper(getActivity());
        ruanganList= dbHandler.getSemuaRuangan();
        adapter = new MyAdapter((ArrayList<DaftarRuangan>) ruanganList);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity().getApplicationContext() , new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        DaftarRuangan bkl = ruanganList.get(position);
                        String nama = bkl.getnama();
                        Intent intent = new Intent(getActivity(), detailRuangan.class);
                        intent.putExtra("ruangan",nama);
                        startActivity(intent);
                        Toast.makeText(getActivity(), "Klik di " + nama, Toast.LENGTH_SHORT).show();
                    }
                })
        );
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        //SEARCH
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                //FILTER AS YOU TYPE
                adapter.getFilter().filter(query);
                return false;
            }
        });
        //cekDataRecyclerView();
        return rootView;

    }


}






