package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class sign_in extends AppCompatActivity {
    EditText email, password;
    Button lOGIN;
    String email1, pass1;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentuser = firebaseAuth.getCurrentUser();

        if (currentuser != null) {

            updateUI();
        }

    }

    private void updateUI() {
        Toast.makeText(getApplicationContext(), "YOU ARE ALREADY LOGGED IN", Toast.LENGTH_LONG).show();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ////////
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        /////////
        email = findViewById(R.id.email);
        password = findViewById(R.id.Password);
        lOGIN = findViewById(R.id.LOGIN);
        //////
        firebaseAuth = FirebaseAuth.getInstance();
        /////
        lOGIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email1 = email.getText().toString();
                pass1 = password.getText().toString();
                if (email1.isEmpty() || pass1.isEmpty()) {

                    Snackbar.make(view, "Neither password or email should be empty", Snackbar.LENGTH_LONG).show();
                } else {
                    firebaseAuth.signInWithEmailAndPassword(email1, pass1)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                        finish();
                                    } else{
                                        Snackbar.make(view, task.getException().getMessage().substring(30), Snackbar.LENGTH_LONG).show();

                                    }
                                }
                            });


                }


            }
        });


    }
}