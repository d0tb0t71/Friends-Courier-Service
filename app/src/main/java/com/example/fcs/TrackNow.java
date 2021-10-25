package com.example.fcs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class TrackNow extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    Button cancel_btn, add_btn;
    EditText name, mobile, address, product_type, sender;
    ArrayList<ModelParcel> list;
    AdapterParcel adapterParcel;

    String senderMobile;
    String MyMobile="";
    String UserStatus="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_now);

        getSupportActionBar().setTitle("Track Now");

        floatingActionButton = findViewById(R.id.floating_action_btn);
        recyclerView = findViewById(R.id.recycler_view);






        recyclerView.setHasFixedSize(true);


        LinearLayoutManager layoutManager = new LinearLayoutManager(TrackNow.this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);


        list = new ArrayList<>();
        adapterParcel = new AdapterParcel(this, list);
        recyclerView.setAdapter(adapterParcel);




        DatabaseReference databaseReference0= FirebaseDatabase.getInstance().getReference("Users");
        FirebaseUser user0= FirebaseAuth.getInstance().getCurrentUser();


        databaseReference0.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){

                    ModelUser modelUser = dataSnapshot.getValue(ModelUser.class);

                    if(user0.getUid().equals(modelUser.getUid())){
                        UserStatus = modelUser.getUserStatus();
                        MyMobile = modelUser.getMobile();

                        if(UserStatus.equals("customer"))
                        {
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Parcel");
                            databaseReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    list.clear();

                                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                        ModelParcel modelParcel = dataSnapshot.getValue(ModelParcel.class);

                                        if(MyMobile.equals(modelParcel.getMobile()))
                                        list.add(modelParcel);
                                    }
                                    adapterParcel.notifyDataSetChanged();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });


                        }
                        else{

                            floatingActionButton.setVisibility(View.VISIBLE);


                            //fetching Data
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Parcel");
                            databaseReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    list.clear();

                                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                        ModelParcel modelParcel = dataSnapshot.getValue(ModelParcel.class);

                                        list.add(modelParcel);
                                    }

                                    adapterParcel.notifyDataSetChanged();

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }









                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Floating Button", Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(TrackNow.this);
                builder.setCancelable(false);

                View view = LayoutInflater.from(TrackNow.this).inflate(R.layout.custom_add_alertdialog, findViewById(R.id.custom_add_dialog));
                builder.setView(view);

                cancel_btn = view.findViewById(R.id.cancel_btn);
                add_btn = view.findViewById(R.id.add_btn);

                name = view.findViewById(R.id.name);
                mobile = view.findViewById(R.id.mobile);
                address = view.findViewById(R.id.address);
                product_type = view.findViewById(R.id.product_type);
                sender = view.findViewById(R.id.sender);


                AlertDialog alertDialog = builder.create();

                cancel_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                add_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String Name = name.getText().toString();
                        String Mobile = mobile.getText().toString();
                        String Address = address.getText().toString();
                        String Product_Type = product_type.getText().toString();
                        String Sender = sender.getText().toString();


                        if (Name.equals("") || Mobile.equals("") || Address.equals("") || Product_Type.equals("")) {
                            Toast.makeText(getApplicationContext(), "Please Input Correct Data", Toast.LENGTH_SHORT).show();
                        } else {

                            HashMap<Object, String> hashMap = new HashMap<>();

                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            String userID = user.getUid();

                            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
                            String currentDateandTime = sdf.format(new Date());

                            DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Users");
                            databaseReference1.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                        senderMobile = "" + dataSnapshot.child("senderMobile").getValue();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                }
                            });

                            hashMap.put("addedBy", userID);
                            hashMap.put("name", Name);
                            hashMap.put("mobile", Mobile);
                            hashMap.put("address", Address);
                            hashMap.put("productType", Product_Type);
                            hashMap.put("date_time", currentDateandTime);
                            hashMap.put("status", "Ready to Ship");
                            hashMap.put("senderMobile", senderMobile);
                            hashMap.put("sender", Sender);

                            hashMap.put("arrivedW", "none");
                            hashMap.put("shipped", "none");
                            hashMap.put("arrivedRW", "none");
                            hashMap.put("delivered", "none");

                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference reference = database.getReference("Parcel");


                            reference.push().setValue(hashMap);

                            Toast.makeText(getApplicationContext(), "Parcel Added Successfully", Toast.LENGTH_SHORT).show();

                            alertDialog.dismiss();
                            recreate();

                        }


                    }

                });

                alertDialog.show();


            }
        });




    }

}