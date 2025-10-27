package com.stukim.triz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private long backPressedTime = 0;
    private static final int BACK_PRESS_INTERVAL = 2000; // 2초

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSearchPrinciples = findViewById(R.id.btnSearchPrinciples);
        Button btnViewAllPrinciples = findViewById(R.id.btnViewAllPrinciples);
        Button btnViewParameters = findViewById(R.id.btnViewParameters);
        Button btnMyMemos = findViewById(R.id.btnMyMemos);
        Button btnAbout = findViewById(R.id.btnAbout);

        btnSearchPrinciples.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(intent);
        });

        btnViewAllPrinciples.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PrinciplesListActivity.class);
            startActivity(intent);
        });

        btnViewParameters.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ParametersListActivity.class);
            startActivity(intent);
        });

        btnMyMemos.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MemoListActivity.class);
            startActivity(intent);
        });

        btnAbout.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        });

        // 뒤로가기 두 번 눌러 종료
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (System.currentTimeMillis() - backPressedTime < BACK_PRESS_INTERVAL) {
                    // 2초 이내에 다시 눌렀을 경우 앱 종료
                    finish();
                } else {
                    // 처음 눌렀을 때 토스트 메시지 표시
                    backPressedTime = System.currentTimeMillis();
                    Toast.makeText(MainActivity.this, "한 번 더 누르면 종료됩니다", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
