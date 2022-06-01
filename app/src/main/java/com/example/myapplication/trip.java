package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class trip extends AppCompatActivity {
    private Spinner from, to, main_loc;
    private Button go, choose;
    private DatabaseReference databaseReference1, databaseReference2;
    final List<String> areas = new ArrayList<String>();
    String from_lat, from_lon, to_lat, to_lon;
    Double from_lat_double, from_lon_double;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);
        from = findViewById(R.id.from);
        main_loc = findViewById(R.id.main_loc);
        to = findViewById(R.id.to);
        choose = findViewById(R.id.choose);

        go = findViewById(R.id.go);

        databaseReference1 = FirebaseDatabase.getInstance().getReference();
        databaseReference2 = FirebaseDatabase.getInstance().getReference();


        String[] loc = {"Select your location", "AMMAN", "IRBID", "AL-SALT", "AJLOUN"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_expandable_list_item_1, loc);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        main_loc.setAdapter(arrayAdapter);
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(trip.this, main_loc.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                databaseReference1.child("bus_stops_names").child(main_loc.getSelectedItem().toString()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        areas.clear();
                        for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                            String areaName = areaSnapshot.getValue(String.class);
                            areas.add(areaName);

                        }

                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_expandable_list_item_1, areas);
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                        from.setAdapter(arrayAdapter);
                        to.setAdapter(arrayAdapter);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference2.child("bus_stops").child(main_loc.getSelectedItem().toString()).child(from.getSelectedItem().toString()).child("lat").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        from_lat = snapshot.getValue(String.class);
                        from_lat_double=Double.parseDouble(from_lat);

                        Toast.makeText(getApplicationContext(), from_lat, Toast.LENGTH_LONG);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                databaseReference2.child("bus_stops").child(main_loc.getSelectedItem().toString()).child(from.getSelectedItem().toString()).child("lon").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        from_lon = snapshot.getValue(String.class);
                        from_lon_double=Double.parseDouble(from_lon);
                        //Toast.makeText(getApplicationContext(), from_lon, Toast.LENGTH_LONG);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                databaseReference2.child("bus_stops").child(main_loc.getSelectedItem().toString()).child(to.getSelectedItem().toString()).child("lat").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        to_lat = snapshot.getValue(String.class);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                databaseReference2.child("bus_stops").child(main_loc.getSelectedItem().toString()).child(to.getSelectedItem().toString()).child("lon").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        to_lon = snapshot.getValue(String.class);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



                String url = "https://www.google.com/maps/dir/?api=1&destination=" + from_lat_double + "," + from_lon_double + "&travelmode=driving";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);


            }
        });


    }
}