package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {
    ProgressDialog progressDialog1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        progressDialog1 = new ProgressDialog(SplashScreen.this);
        progressDialog1.setMessage("Welcome To Easy Way");
        Handler a = new Handler();
        a.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountDownTimer a = new CountDownTimer(1000, 1000) {
                    @Override
                    public void onTick(long l) {
                        progressDialog1.show();
                    }

                    @Override
                    public void onFinish() {
                        startActivity(new Intent(getApplicationContext(), Login.class));
                        finish();
                    }
                }.start();

            }
        }, 1000);
    }


}