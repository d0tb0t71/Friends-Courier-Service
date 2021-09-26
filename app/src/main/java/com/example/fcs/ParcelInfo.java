package com.example.fcs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ParcelInfo extends AppCompatActivity {

    TextView details_productType,details_to,details_mobile,details_address,details_from,details_shippedBy,details_status;
    ImageView arrived_at_warehouse,shipped,arrived_receiver_warehouse,delivered_successfully;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcel_info);

        getSupportActionBar().setTitle("Parcel Details");

        details_productType=findViewById(R.id.details_productType);
        details_to= findViewById(R.id.details_to);
        details_mobile = findViewById(R.id.details_mobile);
        details_address = findViewById(R.id.details_address);
        details_from = findViewById(R.id.details_from);
        details_shippedBy = findViewById(R.id.details_shippedBy);
        details_status = findViewById(R.id.details_status);

        arrived_at_warehouse = findViewById(R.id.arrived_at_warehouse);
        shipped = findViewById(R.id.shipped);
        arrived_receiver_warehouse = findViewById(R.id.arrived_receiver_warehouse);
        delivered_successfully = findViewById(R.id.delivered_successfully);



        details_productType.setText(getIntent().getStringExtra("ProductType"));
        details_to.setText("To: "+getIntent().getStringExtra("Name"));
        details_mobile.setText(getIntent().getStringExtra("Mobile"));
        details_address.setText(getIntent().getStringExtra("Address"));
        details_from.setText("From: "+getIntent().getStringExtra("Sender"));


        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    String ShippedBy = ""+dataSnapshot.child("name").getValue();
                    details_shippedBy.setText("Shipped By: "+ShippedBy);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Parcel");





        arrived_at_warehouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrived_at_warehouse.setColorFilter(getResources().getColor(R.color.green));




            }
        });

        shipped.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shipped.setColorFilter(getResources().getColor(R.color.green));
            }
        });
        arrived_receiver_warehouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrived_receiver_warehouse.setColorFilter(getResources().getColor(R.color.green));
            }
        });
        delivered_successfully.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delivered_successfully.setColorFilter(getResources().getColor(R.color.green));
            }
        });














    }
}