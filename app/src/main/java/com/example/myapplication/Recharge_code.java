package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Recharge_code extends AppCompatActivity {
    int Balance, new_balance;
    Button recharge;
    TextView coderecharge;
    DatabaseReference databaseReference, databaseReference1;
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_code);
        recharge = findViewById(R.id.rechargeB2);
        coderecharge = findViewById(R.id.textView17);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference1 = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        databaseReference.child(firebaseUser.getUid()).child("Balance").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Balance = snapshot.getValue(int.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (coderecharge.getText().toString().length() == 10) {
                    new_balance = Balance + 5;
                    databaseReference1.child(firebaseUser.getUid()).child("Balance").setValue(new_balance);
                    Snackbar.make(view, "Recharged successfully ", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }
}