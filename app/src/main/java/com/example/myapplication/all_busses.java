package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class all_busses extends AppCompatActivity {

    private Spinner spinner_loc;
    private RecyclerView all_buses_rec;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_busses);
        /////
        spinner_loc=findViewById(R.id.spinner_loc);
        all_buses_rec=findViewById(R.id.all_buses_rec);

        /////
        String[] loc = {"Select your location", "AMMAN", "IRBID", "AL-SALT", "AJLOUN"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_expandable_list_item_1, loc);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        /////////////////
        spinner_loc.setAdapter(arrayAdapter);

       // Toast.makeText(getApplicationContext(),spinner_loc.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();

    }
}