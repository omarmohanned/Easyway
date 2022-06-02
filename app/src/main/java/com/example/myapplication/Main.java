package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class Main extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private FirebaseAuth firebaseAuth;
    private String specify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;


        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.busses, R.id.complaints)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);


        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setCheckable(true);
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        Toast.makeText(getApplicationContext(), "Main menu", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.busses:
                        startActivity(new Intent(getApplicationContext(), trip.class));
                        break;
                    case R.id.complaints:
                        startActivity(new Intent(getApplicationContext(), user_complain.class));
                        break;
                    case R.id.home:
                        Intent home_loc = new Intent(getApplicationContext(), location.class);
                        specify = "home";
                        home_loc.putExtra("ret", specify);
                        startActivity(home_loc);
                        break;
                    case R.id.work:
                        Intent work_loc = new Intent(getApplicationContext(), location.class);
                        specify = "work";
                        work_loc.putExtra("ret", specify);
                        startActivity(work_loc);
                        break;
                    case R.id.school:
                        Intent school_loc = new Intent(getApplicationContext(), location.class);
                        specify = "school";
                        school_loc.putExtra("ret", specify);
                        startActivity(school_loc);
                        break;
                    case R.id.tutorial:
                        startActivity(new Intent(getApplicationContext(), tutorial.class));
                        break;
                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(), setting.class));
                        break;
                    case R.id.Scan:
                        startActivity(new Intent(getApplicationContext(), scannerview.class));
                        break;
                    case R.id.sign_out:
                        firebaseAuth.signOut();
                        startActivity(new Intent(getApplicationContext(), SplashScreen.class));
                        finish();
                        break;
                }
                drawer.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}