package com.example.fcs;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class AdapterQuery extends RecyclerView.Adapter<AdapterQuery.MyViewHolder>{


    Context context;
    ArrayList<ModelQuery> list;

    public AdapterQuery(Context context, ArrayList<ModelQuery> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.query_card,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ModelQuery modelQuery = list.get(position);

        String qID = modelQuery.getQsenderID();



        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Users");


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){

                    if(qID.equals(dataSnapshot.child("uid").getValue())){

                        String Name = ""+dataSnapshot.child("name").getValue();
                        String Mobile = ""+dataSnapshot.child("mobile").getValue();

                        holder.query_details.setText(Name+" - "+Mobile);

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        holder.query_txt.setText(modelQuery.getMssg());



        holder.query_call.setOnClickListener(v->{


            String text = holder.query_details.getText().toString();
            String mobile = text.substring(text.length() - 11);

            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:"+mobile));
            context.startActivity(callIntent);

        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder{

        TextView query_details,query_txt;
        ImageView query_call;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            query_details = itemView.findViewById(R.id.query_details);
            query_txt = itemView.findViewById(R.id.query_txt);

            query_call = itemView.findViewById(R.id.query_call);


        }
    }
}