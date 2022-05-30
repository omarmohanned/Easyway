package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class user_complain extends AppCompatActivity {

    private RatingBar ratingBar;
    private Button add_comp;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    String new_id, rating_string;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_complain);

        add_comp = findViewById(R.id.add_comp);
        ratingBar = findViewById(R.id.ratingBar);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        add_comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating_string = String.valueOf(ratingBar.getRating());

                databaseReference.child(firebaseUser.getUid()).child("comp").setValue(rating_string);
                new_id = databaseReference.push().getKey();

                databaseReference.child("all_comp").child(new_id).child("rev_num").setValue(rating_string);
                databaseReference.child("all_comp").child(new_id).child("user").setValue(firebaseUser.getUid());


            }
        });


    }
}