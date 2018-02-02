package com.tugasbesar.jadwal.jadwalkereta.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tugasbesar.jadwal.jadwalkereta.Interface.ItemClickListener;
import com.tugasbesar.jadwal.jadwalkereta.R;

/**
 * Created by Wahyu on 2/1/2018.
 */

public class KeretaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView kereta_name;
    public ImageView kereta_image;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public KeretaViewHolder(View itemView) {
        super(itemView);

        kereta_name = (TextView)itemView.findViewById(R.id.kereta_name);
        kereta_image = (ImageView)itemView.findViewById(R.id.kereta_image);

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        itemClickListener.onClick(view,getAdapterPosition(),false);

    }
}
