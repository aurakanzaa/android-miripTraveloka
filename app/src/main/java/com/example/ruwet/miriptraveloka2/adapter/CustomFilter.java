package com.example.ruwet.miriptraveloka2.adapter;

import android.widget.Filter;

import com.example.ruwet.miriptraveloka2.model.DaftarRuangan;

import java.util.ArrayList;

/**
 * Created by aura on 16/01/18.
 */

class CustomFilter extends Filter {
    MyAdapter adapter;
    ArrayList<DaftarRuangan> filterList;


    public CustomFilter(ArrayList<DaftarRuangan> filterList,MyAdapter adapter)
    {
        this.adapter=adapter;
        this.filterList=filterList;

    }

    //FILTERING OCURS
    @Override
    protected Filter.FilterResults performFiltering(CharSequence constraint) {
        Filter.FilterResults results=new Filter.FilterResults();

        //CHECK CONSTRAINT VALIDITY
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<DaftarRuangan> filteredPlayers=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getnama().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredPlayers.add(filterList.get(i));
                }
            }

            results.count=filteredPlayers.size();
            results.values=filteredPlayers;
        }else
        {
            results.count=filterList.size();
            results.values=filterList;

        }


        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, Filter.FilterResults results) {

        adapter.ruangans= (ArrayList<DaftarRuangan>) results.values;

        //REFRESH
        adapter.notifyDataSetChanged();
    }
}
