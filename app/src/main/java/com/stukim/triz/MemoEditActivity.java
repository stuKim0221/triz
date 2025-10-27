package com.stukim.triz;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MemoEditActivity extends AppCompatActivity {

    private EditText etTarget;
    private Spinner spinnerImproving;
    private Spinner spinnerWorsening;
    private MaterialButton btnRecommend;
    private RecyclerView recyclerRecommendedPrinciples;
    private TextView tvSelectedPrinciples;
    private TextView tvPrompt;
    private MaterialButton btnCopyPrompt;
    private MaterialButton btnSave;

    private MemoManager memoManager;
    private String memoId;

    private List<TrizParameter> parameters;
    private List<InventionPrinciple> allPrinciples;
    private java.util.Map<String, int[]> matrix;
    private PrincipleSelectionAdapter principleAdapter;
    private int selectedImproving = 0;
    private int selectedWorsening = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_edit);

        setupToolbar();
        initViews();
        loadData();
        setupSpinners();
        loadMemoIfEdit();
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
        etTarget = findViewById(R.id.etTarget);
        spinnerImproving = findViewById(R.id.spinnerImproving);
        spinnerWorsening = findViewById(R.id.spinnerWorsening);
        btnRecommend = findViewById(R.id.btnRecommend);
        recyclerRecommendedPrinciples = findViewById(R.id.recyclerRecommendedPrinciples);
        tvSelectedPrinciples = findViewById(R.id.tvSelectedPrinciples);
        tvPrompt = findViewById(R.id.tvPrompt);
        btnCopyPrompt = findViewById(R.id.btnCopyPrompt);
        btnSave = findViewById(R.id.btnSave);

        memoManager = new MemoManager(this);

        // RecyclerView 설정
        principleAdapter = new PrincipleSelectionAdapter();
        recyclerRecommendedPrinciples.setLayoutManager(new LinearLayoutManager(this));
        recyclerRecommendedPrinciples.setAdapter(principleAdapter);

        principleAdapter.setOnPrincipleSelectedListener(selectedId -> {
            updateSelectedPrincipleDisplay(selectedId);
            generatePrompt();
        });

        btnRecommend.setOnClickListener(v -> recommendPrinciples());
        btnCopyPrompt.setOnClickListener(v -> copyPromptToClipboard());
        btnSave.setOnClickListener(v -> saveMemo());

        // 타겟 입력 변경 감지
        etTarget.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                generatePrompt();
            }

            @Override
            public void afterTextChanged(android.text.Editable s) {}
        });
    }

    private void loadData() {
        parameters = TrizData.getParameters();
        allPrinciples = TrizData.getPrinciples();
        matrix = TrizData.getMatrix(this);
    }

    private void recommendPrinciples() {
        if (selectedImproving == 0 || selectedWorsening == 0) {
            Toast.makeText(this, "두 가지 특성을 모두 선택하세요", Toast.LENGTH_SHORT).show();
            return;
        }

        String key = selectedImproving + "_" + selectedWorsening;
        int[] principleIds = matrix.get(key);

        if (principleIds == null || principleIds.length == 0) {
            principleAdapter.setPrinciples(new ArrayList<>());
            tvSelectedPrinciples.setText("해당 조합에 대한 추천 발명원리가 없습니다.");
            return;
        }

        List<InventionPrinciple> recommendedPrinciples = new ArrayList<>();
        for (int id : principleIds) {
            for (InventionPrinciple principle : allPrinciples) {
                if (principle.getId() == id) {
                    recommendedPrinciples.add(principle);
                    break;
                }
            }
        }

        principleAdapter.setPrinciples(recommendedPrinciples);
        Toast.makeText(this, principleIds.length + "개의 원리를 추천합니다", Toast.LENGTH_SHORT).show();
    }

    private void updateSelectedPrincipleDisplay(Integer selectedId) {
        if (selectedId == null) {
            tvSelectedPrinciples.setText("위에서 카드를 선택하세요");
            return;
        }

        InventionPrinciple principle = TrizUtils.findPrincipleById(selectedId);
        if (principle != null) {
            tvSelectedPrinciples.setText(selectedId + "번: " + principle.getName());
        }
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
                generatePrompt();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerWorsening.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedWorsening = parameters.get(position).getId();
                generatePrompt();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void loadMemoIfEdit() {
        memoId = getIntent().getStringExtra("MEMO_ID");
        if (memoId != null) {
            UserMemo memo = memoManager.getMemoById(memoId);
            if (memo != null) {
                etTarget.setText(memo.getTarget());

                // 스피너 위치 설정
                for (int i = 0; i < parameters.size(); i++) {
                    if (parameters.get(i).getId() == memo.getImprovingParameterId()) {
                        spinnerImproving.setSelection(i);
                    }
                    if (parameters.get(i).getId() == memo.getWorseningParameterId()) {
                        spinnerWorsening.setSelection(i);
                    }
                }

                // 선택된 원리 복원
                if (memo.getAppliedPrinciples() != null && !memo.getAppliedPrinciples().isEmpty()) {
                    try {
                        Integer selectedId = Integer.parseInt(memo.getAppliedPrinciples().trim());
                        principleAdapter.setSelectedId(selectedId);
                        updateSelectedPrincipleDisplay(selectedId);
                    } catch (NumberFormatException e) {
                        // Ignore invalid ID
                    }
                }

                // 프롬프트 생성
                generatePrompt();
            }
        }
    }

    private void generatePrompt() {
        String target = etTarget.getText().toString().trim();

        if (target.isEmpty() || selectedImproving == 0 || selectedWorsening == 0) {
            tvPrompt.setText("위 정보를 입력하면 챗봇에게 물어볼 프롬프트가 생성됩니다");
            return;
        }

        String improvingParam = TrizUtils.getParameterName(selectedImproving);
        String worseningParam = TrizUtils.getParameterName(selectedWorsening);

        StringBuilder prompt = new StringBuilder();
        prompt.append("나는 \"").append(target).append("\"에 대해 개선하고자 합니다.\n\n");
        prompt.append("개선하려는 특성: ").append(improvingParam).append("\n");
        prompt.append("악화되는 특성: ").append(worseningParam).append("\n\n");

        Integer selectedId = principleAdapter.getSelectedId();
        if (selectedId != null) {
            InventionPrinciple principle = TrizUtils.findPrincipleById(selectedId);
            if (principle != null) {
                prompt.append("TRIZ 발명원리 ").append(selectedId).append("번 \"").append(principle.getName()).append("\"을 적용하려고 합니다.\n");
                prompt.append("원리 설명: ").append(principle.getDescription()).append("\n\n");
            }
        }

        prompt.append("이 발명원리를 활용하여 구체적인 해결 방안을 제시해주세요.");

        tvPrompt.setText(prompt.toString());
    }

    private void copyPromptToClipboard() {
        String prompt = tvPrompt.getText().toString();

        if (prompt.equals("위 정보를 입력하면 챗봇에게 물어볼 프롬프트가 생성됩니다")) {
            Toast.makeText(this, "먼저 정보를 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }

        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("TRIZ 프롬프트", prompt);
        clipboard.setPrimaryClip(clip);
    }

    private void saveMemo() {
        String target = etTarget.getText().toString().trim();

        // 입력 검증
        if (target.isEmpty()) {
            Toast.makeText(this, "적용 대상을 입력해주세요", Toast.LENGTH_SHORT).show();
            etTarget.requestFocus();
            return;
        }

        if (target.length() < 2) {
            Toast.makeText(this, "적용 대상은 최소 2자 이상 입력해주세요", Toast.LENGTH_SHORT).show();
            etTarget.requestFocus();
            return;
        }

        if (selectedImproving == 0 || selectedWorsening == 0) {
            Toast.makeText(this, "두 가지 특성을 모두 선택하세요", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedImproving == selectedWorsening) {
            Toast.makeText(this, "개선하려는 특성과 악화되는 특성이 같을 수 없습니다", Toast.LENGTH_SHORT).show();
            return;
        }

        // 선택된 원리 ID를 문자열로 변환
        Integer selectedId = principleAdapter.getSelectedId();
        String principlesString = selectedId != null ? String.valueOf(selectedId) : "";

        UserMemo memo = new UserMemo(
                memoId != null ? memoId : "",
                target,
                selectedImproving,
                selectedWorsening,
                principlesString,
                System.currentTimeMillis()
        );

        memoManager.saveMemo(memo);
        Toast.makeText(this, "저장되었습니다", Toast.LENGTH_SHORT).show();
        finish();
    }
}
