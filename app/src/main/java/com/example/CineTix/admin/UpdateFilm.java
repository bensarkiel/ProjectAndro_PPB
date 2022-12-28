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

import com.example.CineTix.admin.model.TambahFilm;
import com.example.CineTix.admin.adapter.UpdateFilmAdapter;
import com.example.CineTix.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateFilm extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UpdateFilmAdapter add_show_adapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recview_update_film);
        getSupportActionBar().setTitle("Update Show");
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

        recyclerView = (RecyclerView)findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<TambahFilm> options = new FirebaseRecyclerOptions.Builder<TambahFilm>().
                setQuery(FirebaseDatabase.getInstance().getReference("Add Show"), TambahFilm.class).build();

        add_show_adapter = new UpdateFilmAdapter(options);
        recyclerView.setAdapter(add_show_adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        add_show_adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
       add_show_adapter.stopListening();
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

        FirebaseRecyclerOptions<TambahFilm> options = new FirebaseRecyclerOptions.Builder<TambahFilm>().
                setQuery(FirebaseDatabase.getInstance().getReference("Add Show").orderByChild("movie_Name").startAt(query).endAt(query+"\uf8ff"), TambahFilm.class).build();

        add_show_adapter =new UpdateFilmAdapter(options);
        add_show_adapter.startListening();
        recyclerView.setAdapter(add_show_adapter);
    }
}