package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class complaints_admin extends AppCompatActivity {
    private RecyclerView admin_compl;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private rev_admin rev_admin;
    private List<ret_rev> ret_rev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_admin);
        admin_compl = findViewById(R.id.admin_compl);


        admin_compl.setHasFixedSize(true);
        admin_compl.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        ret_rev = new ArrayList<>();

        retrive_class();

    }

    private void retrive_class() {


        databaseReference = FirebaseDatabase.getInstance().getReference().child("all_comp");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    ret_rev retrieve = ds.getValue(ret_rev.class);
                    ret_rev.add(retrieve);
                }

                rev_admin = new rev_admin(complaints_admin.this,ret_rev);
                admin_compl.setAdapter(rev_admin);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}