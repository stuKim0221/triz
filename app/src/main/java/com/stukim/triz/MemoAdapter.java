package com.stukim.triz;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> {

    private List<UserMemo> memos = new ArrayList<>();
    private OnMemoClickListener listener;
    private Context context;

    public interface OnMemoClickListener {
        void onMemoClick(UserMemo memo);
        void onFavoriteClick(UserMemo memo);
        void onEditClick(UserMemo memo);
        void onDeleteClick(UserMemo memo);
    }

    public void setOnMemoClickListener(OnMemoClickListener listener) {
        this.listener = listener;
    }

    public void setMemos(List<UserMemo> memos) {
        this.memos = memos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_memo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserMemo memo = memos.get(position);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        holder.tvDate.setText(sdf.format(new Date(memo.getTimestamp())));

        holder.tvTarget.setText(memo.getTarget());

        // 파라미터 이름 찾기
        String improvingName = TrizUtils.getParameterName(memo.getImprovingParameterId());
        String worseningName = TrizUtils.getParameterName(memo.getWorseningParameterId());

        holder.tvImprovingParameter.setText("개선: " + improvingName);
        holder.tvWorseningParameter.setText("악화: " + worseningName);

        // 적용 원리 표시
        String principlesText = memo.getAppliedPrinciples();
        if (principlesText != null && !principlesText.isEmpty()) {
            holder.tvPrinciples.setText("적용 원리: " + principlesText + "번");
        } else {
            holder.tvPrinciples.setText("적용 원리: 미선택");
        }

        // 즐겨찾기 상태에 따라 아이콘 변경
        holder.btnFavorite.setImageResource(
                memo.isFavorite() ? android.R.drawable.star_big_on : android.R.drawable.star_big_off
        );

        // 카드 클릭 이벤트 (itemView가 MaterialCardView 자체)
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onMemoClick(memo);
            }
        });

        holder.btnFavorite.setOnClickListener(v -> {
            if (listener != null) {
                listener.onFavoriteClick(memo);
            }
        });

        holder.btnEdit.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEditClick(memo);
            }
        });

        holder.btnDelete.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDeleteClick(memo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return memos.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate;
        TextView tvTarget;
        TextView tvImprovingParameter;
        TextView tvWorseningParameter;
        TextView tvPrinciples;
        android.widget.ImageButton btnFavorite;
        com.google.android.material.button.MaterialButton btnEdit;
        com.google.android.material.button.MaterialButton btnDelete;

        ViewHolder(View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvMemoDate);
            tvTarget = itemView.findViewById(R.id.tvMemoTarget);
            tvImprovingParameter = itemView.findViewById(R.id.tvImprovingParameter);
            tvWorseningParameter = itemView.findViewById(R.id.tvWorseningParameter);
            tvPrinciples = itemView.findViewById(R.id.tvMemoPrinciples);
            btnFavorite = itemView.findViewById(R.id.btnFavorite);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
