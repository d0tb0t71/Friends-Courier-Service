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

public class AskQuery extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;

    Button cancel_btn,submit_btn;
    EditText mssg;

    String Name,Mobile;

    AdapterQuery adapterQuery;
    ArrayList<ModelQuery> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_query);

        recyclerView=findViewById(R.id.recyclerview);
        floatingActionButton= findViewById(R.id.floating_btn);

        getSupportActionBar().setTitle("Ask Query");




        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(AskQuery.this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        list = new ArrayList<>();

        adapterQuery = new AdapterQuery(this,list);
        recyclerView.setAdapter(adapterQuery);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Query");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ModelQuery modelQuery = dataSnapshot.getValue(ModelQuery.class);

                    list.add(modelQuery);
                }

                adapterQuery.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });










        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(AskQuery.this);
                builder.setCancelable(false);

                View view = LayoutInflater.from(AskQuery.this).inflate(R.layout.custom_query_dialog, findViewById(R.id.custom_query_dialog));
                builder.setView(view);

                cancel_btn = view.findViewById(R.id.cancel_btn);
                submit_btn = view.findViewById(R.id.submit_btn);

                mssg = view.findViewById(R.id.mssg);




                AlertDialog alertDialog = builder.create();

                cancel_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                submit_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String Mssg = mssg.getText().toString();


                        if (Mssg.equals("")) {
                            Toast.makeText(getApplicationContext(), "Please Input Correct Data", Toast.LENGTH_SHORT).show();
                        } else {

                            HashMap<Object, String> hashMap = new HashMap<>();

                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            String userID = user.getUid();



                            hashMap.put("QsenderID", userID);
                            hashMap.put("mssg", Mssg);



                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference reference = database.getReference("Query");


                            reference.push().setValue(hashMap);

                            Toast.makeText(getApplicationContext(), "Query Added Successfully", Toast.LENGTH_SHORT).show();

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