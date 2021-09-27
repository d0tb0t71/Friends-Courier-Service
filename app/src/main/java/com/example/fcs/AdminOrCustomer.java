package com.example.fcs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminOrCustomer extends AppCompatActivity {

    Button regi_customer,regi_admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_or_customer);


        regi_customer = findViewById(R.id.regi_customer);
        regi_admin=findViewById(R.id.regi_admin);


        regi_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), RegisterActivity.class);
                intent.putExtra("userStatus","admin");
                startActivity(intent);

            }
        });

        regi_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), RegisterActivity.class);
                intent.putExtra("userStatus","customer");
                startActivity(intent);

            }
        });




    }
}