package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class all_busses extends AppCompatActivity {

    private Spinner spinner_loc;
    private RecyclerView all_buses_rec;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private all_buses_adapter all_buses_adapter;
    private List<Retrieve_bus_data_base> retrieve_bus_data_bases;
    private Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_busses);
        /////
        spinner_loc = findViewById(R.id.spinner_loc);
        confirm = findViewById(R.id.confirm);
        all_buses_rec = findViewById(R.id.all_buses_rec);

        /////
        all_buses_rec.setHasFixedSize(true);
        all_buses_rec.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        ////
        String[] loc = {"Select your location", "AMMAN", "IRBID", "AL-SALT", "AJLOUN"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_expandable_list_item_1, loc);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        /////////////////
        spinner_loc.setAdapter(arrayAdapter);

        retrieve_bus_data_bases = new ArrayList<>();
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrive_class();
            }
        });


    }

    private void retrive_class() {
        databaseReference=FirebaseDatabase.getInstance().getReference().child("bus_stops").child(spinner_loc.getSelectedItem().toString());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                retrieve_bus_data_bases.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Retrieve_bus_data_base retrieve = ds.getValue(Retrieve_bus_data_base.class);
                    retrieve_bus_data_bases.add(retrieve);
                }


                all_buses_adapter = new all_buses_adapter(all_busses.this, retrieve_bus_data_bases);
                all_buses_rec.setAdapter(all_buses_adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}