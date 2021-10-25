package com.example.fcs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ParcelInfo extends AppCompatActivity {

    Button check_status;
    TextView details_productType,details_to,details_mobile,details_address,details_from,details_shippedBy,details_status;
    ImageView arrived_at_warehouse,shipped,arrived_receiver_warehouse,delivered_successfully;
    HashMap<Object, String> hashMap;
    String MyStatus="" ;

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

        check_status=findViewById(R.id.check_status);


        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Users");
        FirebaseUser user1= FirebaseAuth.getInstance().getCurrentUser();


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){

                    if(user1.getUid().equals(dataSnapshot.child("uid").getValue())){

                        String UserStatus = ""+dataSnapshot.child("userStatus").getValue();
                        if(UserStatus.equals("admin")){
                            check_status.setVisibility(View.GONE);
                        }


                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });







        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String MyUID = user.getUid();


        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    String UserID = ""+dataSnapshot.child("uid").getValue();
                    String UserStatus = ""+dataSnapshot.child("userStatus").getValue();



                    if(UserID.equals(MyUID)){



                        if(UserStatus.equals("admin"))
                        MyStatus=UserStatus;

                        if(MyStatus.equals("admin"))
                        {
                            System.out.println("Admin--------------------------");
                        }
                        else
                        {
                            System.out.println("User--------------------------");

                            arrived_at_warehouse.setVisibility(View.GONE);
                            shipped.setVisibility(View.GONE);
                            arrived_receiver_warehouse.setVisibility(View.GONE);
                            delivered_successfully.setVisibility(View.GONE);

                        }

                    }



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });












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









        arrived_at_warehouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrived_at_warehouse.setColorFilter(getResources().getColor(R.color.green));


                Toast.makeText(getApplicationContext(), "Shipped", Toast.LENGTH_SHORT).show();

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();


                Query query = FirebaseDatabase.getInstance().getReference("Parcel");
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot: snapshot.getChildren())
                        {

                            ModelParcel modelParcel = dataSnapshot.getValue(ModelParcel.class);

                            if(modelParcel.getAddedBy().equals(uid)) {

                                modelParcel.setArrivedW("yes");

                                dataSnapshot.getRef().setValue(modelParcel);

                            }


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }

                });



            }
        });

        shipped.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shipped.setColorFilter(getResources().getColor(R.color.green));


                Toast.makeText(getApplicationContext(), "Shipped", Toast.LENGTH_SHORT).show();

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();


                Query query = FirebaseDatabase.getInstance().getReference("Parcel");
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot: snapshot.getChildren())
                        {

                            ModelParcel modelParcel = dataSnapshot.getValue(ModelParcel.class);

                            if(modelParcel.getAddedBy().equals(uid)) {

                                modelParcel.setShipped("yes");

                                dataSnapshot.getRef().setValue(modelParcel);

                            }


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }

                });
            }
        });
        arrived_receiver_warehouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrived_receiver_warehouse.setColorFilter(getResources().getColor(R.color.green));

                Toast.makeText(getApplicationContext(), "Shipped", Toast.LENGTH_SHORT).show();

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();


                Query query = FirebaseDatabase.getInstance().getReference("Parcel");
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot: snapshot.getChildren())
                        {

                            ModelParcel modelParcel = dataSnapshot.getValue(ModelParcel.class);

                            if(modelParcel.getAddedBy().equals(uid)) {

                                modelParcel.setArrivedRW("yes");

                                dataSnapshot.getRef().setValue(modelParcel);

                            }


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }

                });
            }
        });
        delivered_successfully.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delivered_successfully.setColorFilter(getResources().getColor(R.color.green));


                Toast.makeText(getApplicationContext(), "Shipped", Toast.LENGTH_SHORT).show();

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();


                Query query = FirebaseDatabase.getInstance().getReference("Parcel");
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot: snapshot.getChildren())
                        {

                            ModelParcel modelParcel = dataSnapshot.getValue(ModelParcel.class);

                            if(modelParcel.getAddedBy().equals(uid)) {

                                modelParcel.setDelivered("yes");

                                dataSnapshot.getRef().setValue(modelParcel);

                            }


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }

                });
            }
        });






        String PRODUCTTYPE = getIntent().getStringExtra("ProductType");
        String NAME= getIntent().getStringExtra("Name");
        String MOBILE = getIntent().getStringExtra("Mobile");
        String ADDRESS = getIntent().getStringExtra("Address");
        String SENDER = getIntent().getStringExtra("Sender");


        System.out.println(PRODUCTTYPE+NAME+MOBILE+ADDRESS+SENDER+"========================");






        check_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference db= FirebaseDatabase.getInstance().getReference("Parcel");
                FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();

                db.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                            ModelParcel modelParcel = dataSnapshot.getValue(ModelParcel.class);

                            if(PRODUCTTYPE.equals(modelParcel.getProductType())&&
                                    NAME.equals(modelParcel.getName())&&
                                    MOBILE.equals(modelParcel.getMobile())&&
                                    ADDRESS.equals(modelParcel.getAddress())&&
                                    SENDER.equals(modelParcel.getSender())){

                                if(modelParcel.getDelivered().equals("yes")){
                                    delivered_successfully.setVisibility(View.VISIBLE);
                                }if(modelParcel.getArrivedRW().equals("yes")){
                                    arrived_receiver_warehouse.setVisibility(View.VISIBLE);
                                }if(modelParcel.getShipped().equals("yes")){
                                    shipped.setVisibility(View.VISIBLE);
                                }if(modelParcel.getArrivedW().equals("yes")){
                                    arrived_at_warehouse.setVisibility(View.VISIBLE);
                                }
                                

                            }


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }


                });



            }
        });











    }
}