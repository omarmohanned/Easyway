package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class complaints_admin extends AppCompatActivity {
    private RecyclerView admin_compl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_admin);
        admin_compl = findViewById(R.id.admin_compl);

    }
}