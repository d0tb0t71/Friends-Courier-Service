package com.example.fcs;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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

public class AdapterParcel extends RecyclerView.Adapter<AdapterParcel.MyViewHolder> {

    Context context;
    ArrayList<ModelParcel> list;

    public AdapterParcel(Context context, ArrayList<ModelParcel> list) {

        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.track_card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ModelParcel modelParcel = list.get(position);
        holder.productType.setText(modelParcel.getProductType());
        holder.productStatus.setText("Status: "+modelParcel.getStatus());
        holder.name_owner.setText("To: "+modelParcel.getName());
        holder.sender.setText("From: "+modelParcel.getSender());



      /*  String ProductType = modelParcel.getProductType();
        String Name = modelParcel.getName();
        String Address = modelParcel.getAddress();
        String Mobile = modelParcel.getMobile();
        String  Sender = modelParcel.getSender();
        String ShippedBy = modelParcel.getAddedBy();
        String ParcelStatus = modelParcel.getStatus();


        DatabaseReference db= FirebaseDatabase.getInstance().getReference("Parcel");
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ModelParcel modelParcel = dataSnapshot.getValue(ModelParcel.class);

                    if(modelParcel.getProductType().equals(ProductType)&&
                            modelParcel.getName().equals(Name)&&
                            modelParcel.getAddress().equals(Address)&&
                            modelParcel.getMobile().equals(Mobile)&&
                            modelParcel.getSender().equals(Sender)&&
                            modelParcel.getShipped().equals(ShippedBy)&&
                                    modelParcel.getStatus().equals(ParcelStatus)){

                        if(modelParcel.getDelivered().equals("yes")){
                            holder.productStatus.setText("Delivered");
                            holder.track_card_view.setBackgroundColor(Color.GREEN);
                        }else if(modelParcel.getArrivedRW().equals("yes")){
                            holder.productStatus.setText("Arrived Warehouse");
                        }else if(modelParcel.getShipped().equals("yes")){
                            holder.productStatus.setText("Arrived Receiver Warehouse");
                        }else if(modelParcel.getArrivedW().equals("yes")){
                            holder.productStatus.setText("Ready To Ship");
                        }

                    }






                    }


                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

*/





        holder.info_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,ParcelInfo.class);

                String ProductType = modelParcel.getProductType();
                String Name = modelParcel.getName();
                String Address = modelParcel.getAddress();
                String Mobile = modelParcel.getMobile();
                String  Sender = modelParcel.getSender();
                String ShippedBy = modelParcel.getAddedBy();
                String ParcelStatus = modelParcel.getStatus();



                intent.putExtra("ProductType",ProductType);
                intent.putExtra("Name",Name);
                intent.putExtra("Address",Address);
                intent.putExtra("Mobile",Mobile);
                intent.putExtra("Sender",Sender);
                intent.putExtra("ShippedBy",ShippedBy);
                intent.putExtra("ParcelStatus",ParcelStatus);

                context.startActivity(intent);


            }
        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout track_card_view;
        TextView productType,productStatus,name_owner,sender;
        ImageView info_btn;
        RelativeLayout Track_card_view;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            track_card_view = itemView.findViewById(R.id.track_card_view);

            productType = itemView.findViewById(R.id.productType);
            productStatus = itemView.findViewById(R.id.productStatus);
            name_owner = itemView.findViewById(R.id.name_owner);
            sender = itemView.findViewById(R.id.sender);

            info_btn = itemView.findViewById(R.id.info_btn);

            Track_card_view = itemView.findViewById(R.id.track_card_view);




        }
    }
}
