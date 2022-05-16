package com.example.myapplication.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.location;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private TextView geo_loc;
    private ImageButton fav_home, fav_school, fav_work;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    String lat_home, lon_home,lat_school, lon_school,lat_work, lon_work,specify;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        geo_loc = view.findViewById(R.id.geo_loc);
        fav_home = view.findViewById(R.id.fav_home);
        fav_school = view.findViewById(R.id.fav_school);
        fav_work = view.findViewById(R.id.fav_work);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


        databaseReference.child(firebaseUser.getUid()).child("home").child("lat").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lat_home = snapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference.child(firebaseUser.getUid()).child("home").child("lon").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lon_home = snapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference.child(firebaseUser.getUid()).child("school").child("lat").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lat_school = snapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference.child(firebaseUser.getUid()).child("school").child("lon").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lon_school = snapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference.child(firebaseUser.getUid()).child("work").child("lat").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lat_work = snapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference.child(firebaseUser.getUid()).child("work").child("lon").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lon_work = snapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        fav_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (lat_home.equals("0")||lon_home.equals("0")) {
                    Snackbar.make(view, "Home", Snackbar.LENGTH_LONG).setAction("click to add location", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent home_loc=new Intent(getActivity(),location.class);
                            specify="home";
                            home_loc.putExtra("ret",specify);
                            startActivity(home_loc);

                        }
                    }).show();

                } else {
                    Snackbar.make(view, "Home", Snackbar.LENGTH_LONG).setAction("view Home location", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(getActivity(), location.class));
                        }
                    }).show();
                }


            }


        });

        fav_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lat_work.equals("0")||lon_work.equals("0")) {
                    Snackbar.make(view, "work", Snackbar.LENGTH_LONG).setAction("click to add location", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent work_loc=new Intent(getActivity(),location.class);
                            specify="work";
                            work_loc.putExtra("ret",specify);
                            startActivity(work_loc);

                        }
                    }).show();

                } else {
                    Snackbar.make(view, "work", Snackbar.LENGTH_LONG).setAction("view work location", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(getActivity(), location.class));
                        }
                    }).show();
                }

            }
        });

        fav_school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lat_school.equals("0")||lon_school.equals("0")) {
                    Snackbar.make(view, "school", Snackbar.LENGTH_LONG).setAction("click to add location", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent school_loc=new Intent(getActivity(),location.class);
                            specify="school";
                            school_loc.putExtra("ret",specify);
                            startActivity(school_loc);

                        }
                    }).show();

                } else {
                    Snackbar.make(view, "school", Snackbar.LENGTH_LONG).setAction("view school location", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(getActivity(), location.class));
                        }
                    }).show();
                }

            }
        });
    }
}