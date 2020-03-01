package com.example.pokemonapitest;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = AppConstants.APP_TAG;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListPokemonAdapter adapter = new ListPokemonAdapter();
        RecyclerView recyclerView = findViewById(R.id.rv_pokemon);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, getResources().getInteger(R.integer.span_count)));
        recyclerView.setAdapter(adapter);

        RelativeLayout layoutLoading = findViewById(R.id.progress_loading);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getData().observe(this, adapter::submitList);
        viewModel.getLoading().observe(this, loading -> {
            layoutLoading.setVisibility(loading ? View.VISIBLE : View.GONE);
            recyclerView.setVisibility(!loading ? View.VISIBLE : View.GONE);
        });

    }

}
