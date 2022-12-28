package com.example.CineTix.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.CineTix.R;
import com.example.CineTix.login.Login;
import com.google.firebase.auth.FirebaseAuth;

public class MenuAdmin extends AppCompatActivity {
    private Button add_upcoming_movie,add_show,update_show,view_booking,add_movie,view_feedback,update_Upcoming;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_admin);

        setupUI();


        add_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuAdmin.this, TambahFilm.class));
            }
        });

        update_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuAdmin.this, UpdateFilm.class));
            }
        });

        view_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuAdmin.this, LihatTiket.class));
            }
        });


        view_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuAdmin.this, LihatFeedback.class));
            }
        });

    }

    public void setupUI(){
        add_show = (Button)findViewById(R.id.add_show);
        update_show = (Button)findViewById(R.id.update_show);
        view_booking = (Button)findViewById(R.id.view_booking);
        view_feedback = (Button)findViewById(R.id.view_feedback);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.theatre_Logout: {
                logout();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void logout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Menu Admin");
        builder.setMessage("Are you sure you want to logout ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                firebaseAuth.signOut();
                startActivity(new Intent(MenuAdmin.this, Login.class));
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}