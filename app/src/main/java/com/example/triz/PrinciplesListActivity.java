package com.example.triz;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

public class PrinciplesListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principles_list);

        setupToolbar();

        RecyclerView recyclerView = findViewById(R.id.recyclerPrinciples);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SimplePrincipleAdapter adapter = new SimplePrincipleAdapter();
        recyclerView.setAdapter(adapter);

        // Load all 40 principles
        adapter.setPrinciples(TrizData.getPrinciples());
    }

    private void setupToolbar() {
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
