package com.example.ruwet.miriptraveloka2.adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.ruwet.miriptraveloka2.activity.R;
import com.example.ruwet.miriptraveloka2.model.DaftarRuangan;

import java.util.ArrayList;

import java.util.ArrayList;

/**
 * Created by aura on 16/01/18.
 */

public class MyAdapter extends RecyclerView.Adapter<MyHolder> implements Filterable {
    Context c;
    ArrayList<DaftarRuangan> ruangans,filterList;
    CustomFilter filter;

    public MyAdapter(ArrayList<DaftarRuangan> ruangans)
    {

        this.ruangans=ruangans;
        this.filterList=ruangans;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //CONVERT XML TO VIEW ONBJ
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,null);

        //HOLDER
        MyHolder holder=new MyHolder(v);

        return holder;
    }

    //DATA BOUND TO VIEWS
    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        //BIND DATA
        holder.posTxt.setText(Integer.toString(ruangans.get(position).getHarga()));
        holder.nameTxt.setText(ruangans.get(position).getnama());





        //IMPLEMENT CLICK LISTENET
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Snackbar.make(v,ruangans.get(pos).getnama(),Snackbar.LENGTH_SHORT).show();
            }
        });

    }

    //GET TOTAL NUM OF PLAYERS
    @Override
    public int getItemCount() {
        return ruangans.size();
    }

    //RETURN FILTER OBJ
    @Override
    public Filter getFilter() {
        if(filter==null)
        {
            filter=new CustomFilter(filterList,this);
        }

        return filter;
    }
}
