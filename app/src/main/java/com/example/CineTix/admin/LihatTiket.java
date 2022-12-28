package com.example.CineTix.admin;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.CineTix.admin.adapter.LihatFilmAdapter;
import com.example.CineTix.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class LihatTiket extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LihatFilmAdapter view_booking_adapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recview_admin_lihat_booking);
        getSupportActionBar().setTitle("Lihat Tiket");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorSplash)));
        getWindow().setStatusBarColor((ContextCompat.getColor(this,R.color.colorSplash)));

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
        swipeRefreshLayout.setColorSchemeColors(Color.BLACK);

        recyclerView = (RecyclerView)findViewById(R.id.rec_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<com.example.CineTix.admin.model.LihatTiket> options = new FirebaseRecyclerOptions.Builder<com.example.CineTix.admin.model.LihatTiket>().
                setQuery(FirebaseDatabase.getInstance().getReference("View Booking"), com.example.CineTix.admin.model.LihatTiket.class).build();

        view_booking_adapter = new LihatFilmAdapter(options);
        recyclerView.setAdapter(view_booking_adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        view_booking_adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        view_booking_adapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchview, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("Search Here !!");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ProcessSearch(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String query) {
                ProcessSearch(query);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void ProcessSearch(String query) {
        FirebaseRecyclerOptions<com.example.CineTix.admin.model.LihatTiket> options = new FirebaseRecyclerOptions.Builder<com.example.CineTix.admin.model.LihatTiket>().
                setQuery(FirebaseDatabase.getInstance().getReference("View Booking")
                        .orderByChild("movieName")
                        .startAt(query).endAt(query+ "\uf8ff"), com.example.CineTix.admin.model.LihatTiket.class).build();

        view_booking_adapter = new LihatFilmAdapter(options);
        view_booking_adapter.startListening();
        recyclerView.setAdapter(view_booking_adapter);
    }

}