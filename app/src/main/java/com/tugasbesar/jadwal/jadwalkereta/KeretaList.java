package com.tugasbesar.jadwal.jadwalkereta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.tugasbesar.jadwal.jadwalkereta.Interface.ItemClickListener;
import com.tugasbesar.jadwal.jadwalkereta.Model.Kereta;
import com.tugasbesar.jadwal.jadwalkereta.ViewHolder.KeretaViewHolder;

public class KeretaList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference keretaList;

    String kelasId="";

    FirebaseRecyclerAdapter<Kereta,KeretaViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kereta_list);

        database = FirebaseDatabase.getInstance();
        keretaList = database.getReference("Kereta");

        recyclerView = (RecyclerView)findViewById(R.id.recycler_kereta);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if(getIntent() != null)
            kelasId = getIntent().getStringExtra("KelasId");
        if(!kelasId.isEmpty() && kelasId != null){
            loadListKereta(kelasId);
        }

    }

    private void loadListKereta(String kelasId) {
        adapter = new FirebaseRecyclerAdapter<Kereta, KeretaViewHolder>(Kereta.class,
                R.layout.kereta_item,
                KeretaViewHolder.class,
                keretaList.orderByChild("MenuId").equalTo(kelasId)

                ) {
            @Override
            protected void populateViewHolder(KeretaViewHolder viewHolder, Kereta model, int position) {
                viewHolder.kereta_name.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.kereta_image);

                final Kereta local = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent keretaDetail = new Intent(KeretaList.this,KeretaDetail.class);
                        keretaDetail.putExtra("KeretaId",adapter.getRef(position).getKey());
                        startActivity(keretaDetail);
                    }
                });

            }
        };

        Log.d("TAG",""+adapter.getItemCount());
        recyclerView.setAdapter(adapter);

    }
}
