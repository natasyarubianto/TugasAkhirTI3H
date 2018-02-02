package com.tugasbesar.jadwal.jadwalkereta;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.tugasbesar.jadwal.jadwalkereta.Model.Kereta;

public class KeretaDetail extends AppCompatActivity {

    TextView kereta_name,kereta_price,kereta_tujuan;
    ImageView kereta_image;
    CollapsingToolbarLayout collapsingToolbarLayout;


    String keretaId="";

    FirebaseDatabase database;
    DatabaseReference kereta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kereta_detail);

        database = FirebaseDatabase.getInstance();
        kereta = database.getReference("Kereta");

        kereta_tujuan = (TextView)findViewById(R.id.kereta_tujuan);
        kereta_name = (TextView)findViewById(R.id.kereta_name);
        kereta_price = (TextView)findViewById(R.id.kereta_price);
        kereta_image = (ImageView)findViewById(R.id.img_kereta);

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);


        if(getIntent() != null)
            keretaId = getIntent().getStringExtra("KeretaId");
        if(!keretaId.isEmpty()){

            getDetailKereta(keretaId);
        }


    }

    private void getDetailKereta(String keretaId) {
        kereta.child(keretaId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Kereta kereta = dataSnapshot.getValue(Kereta.class);

                Picasso.with(getBaseContext()).load(kereta.getImage())
                        .into(kereta_image);

                collapsingToolbarLayout.setTitle(kereta.getName());
                kereta_price.setText(kereta.getPrice());
                kereta_name.setText(kereta.getName());
                kereta_tujuan.setText(kereta.getTujuan());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
