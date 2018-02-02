package com.example.ruwet.miriptraveloka2.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ruwet.miriptraveloka2.activity.R;

/**
 * Created by aura on 16/01/18.
 */

public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ImageView gambar;
    TextView nameTxt,posTxt;

    ItemClickListener itemClickListener;


    public MyHolder(View itemView) {
        super(itemView);

        this.nameTxt= (TextView) itemView.findViewById(R.id.nameTxt);
        this.posTxt= (TextView) itemView.findViewById(R.id.posTxt);
//        this.gambar= (ImageView) itemView.findViewById(R.id.gambar);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v,getLayoutPosition());

    }

    public void setItemClickListener(ItemClickListener ic)
    {
        this.itemClickListener=ic;
    }
}
