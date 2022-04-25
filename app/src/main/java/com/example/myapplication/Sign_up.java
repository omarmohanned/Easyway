package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Sign_up extends AppCompatActivity {
    EditText first_name, last_name, St_id, Password, Conf_pass, email, Age;
    Spinner Location, Handicap;
    Button register;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ///////////////
        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);
        St_id = findViewById(R.id.St_id);
        Password = findViewById(R.id.Password);
        Conf_pass = findViewById(R.id.Conf_pass);
        email = findViewById(R.id.email);
        Age = findViewById(R.id.Age);
        //////
        Location = findViewById(R.id.Location);
        Handicap = findViewById(R.id.Handicap);
        //////////
        register = findViewById(R.id.Register);
        /////////////
        firebaseAuth = FirebaseAuth.getInstance();
        ///////////
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), Password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Snackbar.make(view, "completed", Snackbar.LENGTH_LONG).show();

                                } else {
                                    String Response = task.getException().getMessage();
                                    Snackbar.make(view, Response, Snackbar.LENGTH_LONG).show();


                                }

                            }
                        });


                if (first_name.getText().toString().isEmpty() && last_name.getText().toString().isEmpty()) {
                    Snackbar.make(view, "Neither first name or last name can be empty", Snackbar.LENGTH_LONG).show();

                } else if (St_id.getText().toString().length() < 10) {
                    Snackbar.make(view, "ID CANT BE LESS THAN 10 NUMBERS ", Snackbar.LENGTH_LONG).show();

                } else if (Password.getText().toString().isEmpty()) {
                    Snackbar.make(view, "Password should not be empty", Snackbar.LENGTH_LONG).show();

                } else if (Password.getText().toString().contains("@") || (Password.getText().toString().contains("$") || (Password.getText().toString().contains("_")))) {


                } else if (!(Password.getText().toString() == Conf_pass.getText().toString())) {
                    Snackbar.make(view, "Password does not match", Snackbar.LENGTH_LONG).show();

                } else if (email.getText().toString().isEmpty()) {

                    Snackbar.make(view, "Email cant be empty", Snackbar.LENGTH_LONG).show();

                } else {

                }


            }
        });


    }
}