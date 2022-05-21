package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class sign_in_fragment extends Fragment {
    EditText email, password;
    Button lOGIN;
    String email1, pass1;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;


    public sign_in_fragment() {
        // Required empty public constructor
    }


    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if (currentUser != null) {
            updateUI();
        }

    }

    private void updateUI() {
        Toast.makeText(getContext(), "you are logged in", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getActivity().getApplicationContext(), Main.class));
    }

    public static sign_in_fragment newInstance(String param1, String param2) {
        sign_in_fragment fragment = new sign_in_fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ////////
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        /////////
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.Password);
        lOGIN = view.findViewById(R.id.LOGIN);
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

                } else if (email1.equals("admin@gmail.com") && pass1.equals("admin1234")) {

                    startActivity(new Intent(getContext(),Admin_page.class));
                    getActivity().finish();

                } else {
                    firebaseAuth.signInWithEmailAndPassword(email1, pass1)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(getContext(), Main.class));
                                        getActivity().finish();
                                    } else {
                                        Snackbar.make(view, task.getException().getMessage().substring(30), Snackbar.LENGTH_LONG).show();

                                    }
                                }
                            });
                }


            }



    });

}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in_fragment, container, false);
    }
}