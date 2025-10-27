package com.example.triz;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchActivity extends AppCompatActivity {

    private Spinner spinnerImproving;
    private Spinner spinnerWorsening;
    private Button btnSearch;
    private TextView tvResult;
    private RecyclerView recyclerPrinciples;
    private SimplePrincipleAdapter adapter;

    private List<TrizParameter> parameters;
    private List<InventionPrinciple> allPrinciples;
    private Map<String, int[]> matrix;

    private int selectedImproving = 0;
    private int selectedWorsening = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setupToolbar();
        initViews();
        loadData();
        setupSpinners();
        setupRecyclerView();
        setupSearchButton();
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

    private void initViews() {
        spinnerImproving = findViewById(R.id.spinnerImproving);
        spinnerWorsening = findViewById(R.id.spinnerWorsening);
        btnSearch = findViewById(R.id.btnSearch);
        tvResult = findViewById(R.id.tvResult);
        recyclerPrinciples = findViewById(R.id.recyclerPrinciples);
    }

    private void loadData() {
        parameters = TrizData.getParameters();
        allPrinciples = TrizData.getPrinciples();
        matrix = TrizData.getMatrix(this);
    }

    private void setupSpinners() {
        ArrayAdapter<TrizParameter> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                parameters
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerImproving.setAdapter(adapter);
        spinnerWorsening.setAdapter(adapter);

        spinnerImproving.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedImproving = parameters.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerWorsening.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedWorsening = parameters.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setupRecyclerView() {
        adapter = new SimplePrincipleAdapter();
        recyclerPrinciples.setLayoutManager(new LinearLayoutManager(this));
        recyclerPrinciples.setAdapter(adapter);
    }

    private void setupSearchButton() {
        btnSearch.setOnClickListener(v -> searchPrinciples());
    }

    private void searchPrinciples() {
        if (selectedImproving == 0 || selectedWorsening == 0) {
            Toast.makeText(this, "두 가지 특성을 모두 선택하세요", Toast.LENGTH_SHORT).show();
            return;
        }

        String key = selectedImproving + "_" + selectedWorsening;
        int[] principleIds = matrix.get(key);

        if (principleIds == null || principleIds.length == 0) {
            tvResult.setText("해당 조합에 대한 발명원리가 없습니다.");
            adapter.setPrinciples(new ArrayList<>());
            Toast.makeText(this, "발명원리를 찾을 수 없습니다", Toast.LENGTH_SHORT).show();
            return;
        }

        List<InventionPrinciple> resultPrinciples = new ArrayList<>();
        for (int id : principleIds) {
            for (InventionPrinciple principle : allPrinciples) {
                if (principle.getId() == id) {
                    resultPrinciples.add(principle);
                    break;
                }
            }
        }

        tvResult.setText("추천 발명원리 (" + resultPrinciples.size() + "개):");
        adapter.setPrinciples(resultPrinciples);
    }
}
