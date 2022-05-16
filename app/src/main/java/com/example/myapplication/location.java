package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.AlertDialog;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import java.util.List;
import java.util.Locale;

public class location extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityLocationBinding binding;
    static final int requst_libary_picyure = 3;
    public Geocoder geocoder;
    public double lat_intent, lon_intent;
    List<Address> addresses;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private StorageReference storageReference;
    private Uri imageuri;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser, firebaseUser1;
    private String start_lon, start_lat, specify_button;
    private DatabaseReference databaseReference, databaseReference1, databaseReference2;


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

        LatLng amman = new LatLng(31.946868, 35.909918);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(amman));
/////////
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firebaseUser1 = FirebaseAuth.getInstance().getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference1 = FirebaseDatabase.getInstance().getReference();
        databaseReference2 = FirebaseDatabase.getInstance().getReference();
////////////
        Intent getintent = getIntent();
        specify_button = getintent.getStringExtra("ret");
        Toast.makeText(getApplicationContext(), specify_button, Toast.LENGTH_LONG).show();
/////////
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(@NonNull LatLng latLng) {
                take_order();
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
                }else {
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