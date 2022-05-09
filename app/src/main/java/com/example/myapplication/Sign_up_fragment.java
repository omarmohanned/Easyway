package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Sign_up_fragment extends Fragment {

    EditText first_name, last_name, St_id, Password, Conf_pass, email, Age;
    Spinner Location, Handicap;
    Button register;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    Double Balance = 2.0;
    private String fullname;


    public static Sign_up_fragment newInstance(String param1, String param2) {
        Sign_up_fragment fragment = new Sign_up_fragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    public Sign_up_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ///////////////
        first_name = view.findViewById(R.id.first_name);
        last_name = view.findViewById(R.id.last_name);
        St_id = view.findViewById(R.id.St_id);
        Password = view.findViewById(R.id.Password);
        Conf_pass = view.findViewById(R.id.Conf_pass);
        email = view.findViewById(R.id.email);
        Age = view.findViewById(R.id.Age);
        Location = view.findViewById(R.id.Location);
        Handicap = view.findViewById(R.id.Handicap);
        //////////

        String[] loc = {"Select your location", "AMMAN", "IRBID", "AL-SALT", "AJLOUN"};
        String[] han = {"Select from below", "YES", "NO"};


        /////////////////////
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_expandable_list_item_1, loc);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        ///////////////////

        //////////////////
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(getContext(), android.R.layout.simple_expandable_list_item_1, han);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        /////////////////
        Handicap.setAdapter(arrayAdapter2);
        Location.setAdapter(arrayAdapter);
        ////////

        St_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Student id should be 10 digits", Snackbar.LENGTH_LONG).show();
            }
        });
        ///////
        Location = view.findViewById(R.id.Location);
        Handicap = view.findViewById(R.id.Handicap);
        //////////
        register = view.findViewById(R.id.Register);
        /////////////
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
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
                                    firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                                    ////////
                                    fullname = first_name.getText().toString() + " " + last_name.getText().toString();
                                    databaseReference.child(firebaseUser.getUid()).child("full_name").setValue(fullname);
                                    databaseReference.child(firebaseUser.getUid()).child("email").setValue(email.getText().toString());

                                    databaseReference.child(firebaseUser.getUid()).child("Age").setValue(Age.getText().toString());
                                    databaseReference.child(firebaseUser.getUid()).child("Ststus").setValue(true);
                                    databaseReference.child(firebaseUser.getUid()).child("Balance").setValue(Balance);
                                    ///////

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_up_fragment, container, false);
    }
}