package com.example.fcs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    Button login_now,go_register_now;
    EditText email_login_ET,password_login_ET;
    TextView forgot_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        forgot_pass=findViewById(R.id.forgot_pass_TV);

        login_now=findViewById(R.id.login_now);
        go_register_now=findViewById(R.id.go_register_now);

        email_login_ET=findViewById(R.id.email_login_ET);
        password_login_ET=findViewById(R.id.password_login_ET);


        forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Forgot Password", Toast.LENGTH_SHORT).show();
            }
        });

        login_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=email_login_ET.getText().toString();
                String pass=password_login_ET.getText().toString();

                FirebaseAuth mAuth=FirebaseAuth.getInstance();

                mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(getApplicationContext(),HomeActivity.class));

                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Login Failed\n"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


                Toast.makeText(getApplicationContext(), "Login Now", Toast.LENGTH_SHORT).show();
            }
        });

        go_register_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });




    }
}