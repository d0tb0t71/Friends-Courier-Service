package com.example.fcs;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    ImageView track_now,ask_query,my_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        track_now=findViewById(R.id.track_now);
        ask_query=findViewById(R.id.ask_query);
        my_profile=findViewById(R.id.my_profile);

        track_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),TrackNow.class));
            }
        });

        ask_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),AskQuery.class));

            }
        });

        my_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),MyProfile.class));

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}