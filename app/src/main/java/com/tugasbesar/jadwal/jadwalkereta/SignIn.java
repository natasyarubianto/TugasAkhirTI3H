package com.tugasbesar.jadwal.jadwalkereta;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.tugasbesar.jadwal.jadwalkereta.Adapter.Umum;
import com.tugasbesar.jadwal.jadwalkereta.Model.User;

public class SignIn extends AppCompatActivity{

    EditText edtNim, edtPassword;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        edtPassword = (MaterialEditText)findViewById(R.id.edtPassword);
        edtNim = (MaterialEditText)findViewById(R.id.edtNim);
        btnSignIn = (Button)findViewById(R.id.btnSignIn);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                mDialog.setMessage("Please waiting..");
                mDialog.show();


                table_user.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if(dataSnapshot.child(edtNim.getText().toString()).exists()) {

                            mDialog.dismiss();
                            User user = dataSnapshot.child(edtNim.getText().toString()).getValue(User.class);
                            if(user.getPassword().equals(edtPassword.getText().toString())) {
                                {
                                    Intent homeIntent = new Intent (SignIn.this,Home.class);
                                    Umum.currentUser = user;
                                    startActivity(homeIntent);
                                    finish();
                                }
                            }
                            else{
                                Toast.makeText(SignIn.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else{
                            Toast.makeText(SignIn.this, "User not exist in Database", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
