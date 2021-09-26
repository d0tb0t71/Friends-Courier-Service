package com.example.fcs;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            track_card_view = itemView.findViewById(R.id.track_card_view);

            productType = itemView.findViewById(R.id.productType);
            productStatus = itemView.findViewById(R.id.productStatus);
            name_owner = itemView.findViewById(R.id.name_owner);
            sender = itemView.findViewById(R.id.sender);

            info_btn = itemView.findViewById(R.id.info_btn);




        }
    }
}
