package com.example.myapplication;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.myapplication.databinding.ActivityLocationBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class location extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityLocationBinding binding;
    public Geocoder geocoder;
    public double lat_intent, lon_intent;
    List<Address> addresses;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private StorageReference storageReference;
    private Uri imageuri;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser, firebaseUser1;
    private String start_lon;
    private String start_lat;
    private String specify_button;
    private String stop_lat;
    private String stop_lon;
    private String Fulladdress;
    private String home_lat, home_lon, school_lat, school_lon, work_lat, work_lon;
    private double home_lat_dou, home_lon_dou, school_lat_dou, school_lon_dou, work_lat_dou, work_lon_dou;
    private DatabaseReference databaseReference, databaseReference1, databaseReference2, databaseReference4;
    private EditText fee, stop_name;
    private Button admit;
    private Spinner Route_name;
    private TextView lat_lon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firebaseUser1 = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    protected void onStart() {
        location();
        geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        super.onStart();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        location();


        LatLng amman = new LatLng(31.946868, 35.909918);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(amman));
/////////
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firebaseUser1 = FirebaseAuth.getInstance().getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference1 = FirebaseDatabase.getInstance().getReference();
        databaseReference2 = FirebaseDatabase.getInstance().getReference();
        databaseReference4 = FirebaseDatabase.getInstance().getReference();
////////////
        Intent getintent = getIntent();
        specify_button = getintent.getStringExtra("ret");
        Toast.makeText(getApplicationContext(), specify_button, Toast.LENGTH_LONG).show();

        if (specify_button.equals("admin_loc")) {
            mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                @Override
                public void onMapLongClick(@NonNull LatLng latLng) {
                    add_route_admin();
                    Toast.makeText(getApplicationContext(), "BUS STOPS", Toast.LENGTH_LONG).show();
                }
            });

        } else if (specify_button.equals("home")) {
            mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                @Override
                public void onMapLongClick(@NonNull LatLng latLng) {
                    take_order();
                }
            });

        } else if (specify_button.equals("work")) {

            mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                @Override
                public void onMapLongClick(@NonNull LatLng latLng) {
                    take_order();
                }
            });
        } else if (specify_button.equals("school")) {
            mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                @Override
                public void onMapLongClick(@NonNull LatLng latLng) {
                    take_order();
                }
            });
        } else if (specify_button.equals("home_place")) {
            mMap.clear();
            databaseReference4.child(firebaseUser.getUid()).child("home").child("lat").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    home_lat = snapshot.getValue(String.class);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            databaseReference4.child(firebaseUser.getUid()).child("home").child("lon").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    home_lon = snapshot.getValue(String.class);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            LatLng home_1 = new LatLng(31.966523297914733, 35.91354891657829);
            mMap.addMarker(new MarkerOptions().position(home_1).title("Home location"));

        } else if (specify_button.equals("work_place")) {

        } else if (specify_button.equals("school_place")) {

        }

    }

    private void add_route_admin() {
        mMap.clear();
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(@NonNull LatLng latLng) {

                mMap.addMarker(new MarkerOptions().position(latLng).title("your location"));

                double lat = latLng.latitude;
                double lon = latLng.longitude;
                ////////////
                stop_lon = String.valueOf(lon);
                stop_lat = String.valueOf(lat);


                final Dialog dialog = new Dialog(location.this);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.add_new_route);
                String[] loc = {"Select your location", "AMMAN", "IRBID", "AL-SALT", "AJLOUN"};


                try {
                    addresses = geocoder.getFromLocation(lat, lon, 10);

                    String address = addresses.get(0).getAddressLine(0);
                    String area = addresses.get(0).getLocality();
                    String city = addresses.get(0).getAdminArea();
                    String country = addresses.get(0).getCountryName();

                    Fulladdress = address + " " + area + " " + city + " " + country;
                    ///SEE IT

                } catch (IOException e) {
                    e.printStackTrace();
                }


                //Fulladdress
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_expandable_list_item_1, loc);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                /////////////////
                Route_name = dialog.findViewById(R.id.Route_name);
                fee = dialog.findViewById(R.id.fee);
                lat_lon = dialog.findViewById(R.id.lat_lon);
                admit = dialog.findViewById(R.id.admit);
                stop_name = dialog.findViewById(R.id.stop_name);

                Route_name.setAdapter(arrayAdapter);
                lat_lon.setText(Fulladdress);

                admit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mMap.clear();

                        if (Route_name.getSelectedItem().toString() != "Select your location") {
                            databaseReference1.child("bus_stops").child(Route_name.getSelectedItem().toString()).child(stop_name.getText().toString()).child("lat").setValue(stop_lat);
                            databaseReference1.child("bus_stops").child(Route_name.getSelectedItem().toString()).child(stop_name.getText().toString()).child("lon").setValue(stop_lon);
                            databaseReference1.child("bus_stops").child(Route_name.getSelectedItem().toString()).child(stop_name.getText().toString()).child("price").setValue(fee.getText().toString());
                            databaseReference1.child("bus_stops").child(Route_name.getSelectedItem().toString()).child(stop_name.getText().toString()).child("ADDRESS").setValue(Fulladdress);
                            ///////////
                            databaseReference1.child("bus_stops").child(Route_name.getSelectedItem().toString()).child(stop_name.getText().toString()).child("busStopName").setValue(stop_name.getText().toString());
                            //////////
                            databaseReference2.child("bus_stops_names").child(Route_name.getSelectedItem().toString()).child(stop_name.getText().toString()).setValue(stop_name.getText().toString());
                            ////////////
                            Toast.makeText(getApplicationContext(), "Bus stop Added successfully", Toast.LENGTH_LONG).show();
                        } else {

                            Toast.makeText(getApplicationContext(), "SELECT FROM THE SPINNER", Toast.LENGTH_LONG).show();
                        }

                    }
                });

                dialog.show();

            }

        });


    }


    private void take_order() {
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(@NonNull LatLng latLng) {
                mMap.clear();
                double lat = latLng.latitude;
                double lon = latLng.longitude;
                ////////////
                start_lon = String.valueOf(lon);
                start_lat = String.valueOf(lat);

                if (specify_button != null) {
                    databaseReference.child(firebaseUser.getUid()).child(specify_button).child("lat").setValue(start_lat);
                    databaseReference.child(firebaseUser.getUid()).child(specify_button).child("lon").setValue(start_lon);
                    mMap.addMarker(new MarkerOptions().position(latLng).title("your location"));
                } else {
                    mMap.addMarker(new MarkerOptions().position(latLng).title("your location"));

                }


            }
        });


    }

    private void location() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                start_lon = String.valueOf(location.getLongitude());
                start_lat = String.valueOf(location.getLatitude());


            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}, 10);
                return;
            } else {
                locationManager.requestLocationUpdates("gps", 1000, 5, locationListener);
            }
        }
    }
}