package com.example.triz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MemoListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MemoAdapter adapter;
    private MemoManager memoManager;
    private FloatingActionButton fabAddMemo;
    private com.google.android.material.chip.Chip chipFavorites;
    private boolean showOnlyFavorites = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_list);

        setupToolbar();
        initViews();
        setupRecyclerView();
        setupFilterChip();
        loadMemos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadMemos();
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
        recyclerView = findViewById(R.id.recyclerMemos);
        fabAddMemo = findViewById(R.id.fabAddMemo);
        chipFavorites = findViewById(R.id.chipFavorites);
        memoManager = new MemoManager(this);

        fabAddMemo.setOnClickListener(v -> {
            Intent intent = new Intent(MemoListActivity.this, MemoEditActivity.class);
            startActivity(intent);
        });
    }

    private void setupFilterChip() {
        chipFavorites.setOnCheckedChangeListener((buttonView, isChecked) -> {
            showOnlyFavorites = isChecked;
            loadMemos();
        });
    }

    private void setupRecyclerView() {
        adapter = new MemoAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setOnMemoClickListener(new MemoAdapter.OnMemoClickListener() {
            @Override
            public void onMemoClick(UserMemo memo) {
                showMemoDetailDialog(memo);
            }

            @Override
            public void onFavoriteClick(UserMemo memo) {
                memo.setFavorite(!memo.isFavorite());
                memoManager.saveMemo(memo);
                loadMemos();
            }

            @Override
            public void onEditClick(UserMemo memo) {
                Intent intent = new Intent(MemoListActivity.this, MemoEditActivity.class);
                intent.putExtra("MEMO_ID", memo.getId());
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(UserMemo memo) {
                showDeleteDialog(memo);
            }
        });
    }

    private void loadMemos() {
        List<UserMemo> allMemos = memoManager.getAllMemos();

        if (showOnlyFavorites) {
            List<UserMemo> favoriteMemos = new ArrayList<>();
            for (UserMemo memo : allMemos) {
                if (memo.isFavorite()) {
                    favoriteMemos.add(memo);
                }
            }
            adapter.setMemos(favoriteMemos);
        } else {
            adapter.setMemos(allMemos);
        }
    }

    private void showMemoDetailDialog(UserMemo memo) {
        Dialog dialog = new Dialog(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_memo_detail, null);
        dialog.setContentView(dialogView);

        // 뷰 찾기
        TextView tvDialogTarget = dialogView.findViewById(R.id.tvDialogTarget);
        TextView tvDialogImproving = dialogView.findViewById(R.id.tvDialogImproving);
        TextView tvDialogWorsening = dialogView.findViewById(R.id.tvDialogWorsening);
        TextView tvDialogPrincipleNumber = dialogView.findViewById(R.id.tvDialogPrincipleNumber);
        TextView tvDialogPrincipleName = dialogView.findViewById(R.id.tvDialogPrincipleName);
        TextView tvDialogPrincipleDescription = dialogView.findViewById(R.id.tvDialogPrincipleDescription);
        TextView tvDialogPrompt = dialogView.findViewById(R.id.tvDialogPrompt);
        MaterialButton btnDialogCopy = dialogView.findViewById(R.id.btnDialogCopy);
        MaterialButton btnDialogClose = dialogView.findViewById(R.id.btnDialogClose);

        // 파라미터 정보 설정
        String improvingParam = TrizUtils.getParameterName(memo.getImprovingParameterId());
        String worseningParam = TrizUtils.getParameterName(memo.getWorseningParameterId());

        tvDialogTarget.setText(memo.getTarget());
        tvDialogImproving.setText(improvingParam);
        tvDialogWorsening.setText(worseningParam);

        // 발명원리 정보 설정
        String principleIdStr = memo.getAppliedPrinciples();
        if (principleIdStr != null && !principleIdStr.isEmpty()) {
            try {
                int principleId = Integer.parseInt(principleIdStr);
                InventionPrinciple principle = TrizUtils.findPrincipleById(principleId);

                if (principle != null) {
                    tvDialogPrincipleNumber.setText(principleId + "번");
                    tvDialogPrincipleName.setText(principle.getName());
                    tvDialogPrincipleDescription.setText(principle.getDescription());
                } else {
                    tvDialogPrincipleNumber.setText("미선택");
                    tvDialogPrincipleName.setText("");
                    tvDialogPrincipleDescription.setText("");
                }
            } catch (NumberFormatException e) {
                tvDialogPrincipleNumber.setText("미선택");
                tvDialogPrincipleName.setText("");
                tvDialogPrincipleDescription.setText("");
            }
        } else {
            tvDialogPrincipleNumber.setText("미선택");
            tvDialogPrincipleName.setText("");
            tvDialogPrincipleDescription.setText("");
        }

        // 프롬프트 생성
        String prompt = generatePrompt(memo);
        tvDialogPrompt.setText(prompt);

        // 버튼 이벤트
        btnDialogCopy.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("TRIZ 프롬프트", prompt);
            clipboard.setPrimaryClip(clip);
        });

        btnDialogClose.setOnClickListener(v -> dialog.dismiss());

        // 다이얼로그 크기 설정
        dialog.getWindow().setLayout(
                (int) (getResources().getDisplayMetrics().widthPixels * 0.9),
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT
        );

        dialog.show();
    }

    private String generatePrompt(UserMemo memo) {
        // 파라미터 이름 찾기
        String improvingParam = TrizUtils.getParameterName(memo.getImprovingParameterId());
        String worseningParam = TrizUtils.getParameterName(memo.getWorseningParameterId());

        // 프롬프트 생성
        StringBuilder prompt = new StringBuilder();
        prompt.append("나는 \"").append(memo.getTarget()).append("\"에 대해 개선하고자 합니다.\n\n");
        prompt.append("개선하려는 특성: ").append(improvingParam).append("\n");
        prompt.append("악화되는 특성: ").append(worseningParam).append("\n\n");

        String principleIdStr = memo.getAppliedPrinciples();
        if (principleIdStr != null && !principleIdStr.isEmpty()) {
            try {
                int principleId = Integer.parseInt(principleIdStr);
                InventionPrinciple principle = TrizUtils.findPrincipleById(principleId);

                if (principle != null) {
                    prompt.append("TRIZ 발명원리 ").append(principleId).append("번 \"").append(principle.getName()).append("\"을 적용하려고 합니다.\n");
                    prompt.append("원리 설명: ").append(principle.getDescription()).append("\n\n");
                }
            } catch (NumberFormatException e) {
                // Ignore
            }
        }

        prompt.append("이 발명원리를 활용하여 구체적인 해결 방안을 제시해주세요.");

        return prompt.toString();
    }

    private void showDeleteDialog(UserMemo memo) {
        new AlertDialog.Builder(this)
                .setTitle("메모 삭제")
                .setMessage("이 메모를 삭제하시겠습니까?")
                .setPositiveButton("삭제", (dialog, which) -> {
                    memoManager.deleteMemo(memo.getId());
                    loadMemos();
                })
                .setNegativeButton("취소", null)
                .show();
    }
}
