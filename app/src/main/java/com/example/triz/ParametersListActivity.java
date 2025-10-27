package com.example.triz;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

public class ParametersListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameters_list);

        setupToolbar();

        RecyclerView recyclerView = findViewById(R.id.recyclerParameters);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ParameterAdapter adapter = new ParameterAdapter();
        recyclerView.setAdapter(adapter);

        // Load all 39 parameters
        adapter.setParameters(TrizData.getParameters());
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
