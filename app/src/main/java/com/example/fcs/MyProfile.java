package com.example.fcs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyProfile extends AppCompatActivity {

    TextView user_name,user_mobile,user_address,user_email;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        getSupportActionBar().setTitle("My Profile");

        user_name=findViewById(R.id.user_name);
        user_mobile=findViewById(R.id.user_mobile);
        user_address=findViewById(R.id.user_address);
        user_email=findViewById(R.id.user_email);

        logout=findViewById(R.id.logout);


        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Users");
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){

                    if(user.getUid().equals(dataSnapshot.child("uid").getValue())){

                        String Name = ""+dataSnapshot.child("name").getValue();
                        String Mobile = ""+dataSnapshot.child("mobile").getValue();
                        String Address = ""+dataSnapshot.child("address").getValue();
                        String Email = ""+dataSnapshot.child("email").getValue();
                        user_name.setText(Name);
                        user_mobile.setText(Mobile);
                        user_address.setText(Address);
                        user_email.setText(Email);

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });




    }
}