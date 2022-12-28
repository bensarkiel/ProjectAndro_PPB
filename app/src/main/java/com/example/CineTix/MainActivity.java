package com.example.CineTix;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.CineTix.login.Login;
import com.example.CineTix.theatreactivity.TheatreActivity;
import com.example.CineTix.useractivity.UserActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.CineTix.R.color.colorSplash;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        getSupportActionBar().hide();
        getWindow().setStatusBarColor((ContextCompat.getColor(getApplicationContext(), colorSplash)));

        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                if (firebaseUser != null){
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User Info").child(firebaseAuth.getUid());
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String value = String.valueOf(snapshot.child("usertype").getValue());
                            int value2 = Integer.parseInt(value);
                            if (value2 == 1){
                                startActivity(new Intent(MainActivity.this, UserActivity.class));
                            }else {
                                startActivity(new Intent(MainActivity.this, TheatreActivity.class));
                            }
                            finish();
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    startActivity(new Intent(MainActivity.this, Login.class));
                    finish();
                }
            }
        },1800);
    }
}