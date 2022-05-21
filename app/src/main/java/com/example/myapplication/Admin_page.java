package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Admin_page extends AppCompatActivity {
    private ImageView bus_Stop, complaints;
    private String specify;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        bus_Stop = findViewById(R.id.bus_Stop);
        complaints = findViewById(R.id.complaints);
        bus_Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent admin=new Intent(getApplicationContext(),location.class);
                specify="admin_loc";
                admin.putExtra("ret",specify);
                startActivity(admin);

            }
        });
        complaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(getApplicationContext(), complaints_admin.class));

            }
        });
    }
}