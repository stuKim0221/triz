package com.stukim.triz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PrincipleSelectionAdapter extends RecyclerView.Adapter<PrincipleSelectionAdapter.ViewHolder> {

    private List<InventionPrinciple> principles = new ArrayList<>();
    private Integer selectedId = null;
    private OnPrincipleSelectedListener listener;

    public interface OnPrincipleSelectedListener {
        void onPrincipleSelected(Integer selectedId);
    }

    public void setOnPrincipleSelectedListener(OnPrincipleSelectedListener listener) {
        this.listener = listener;
    }

    public void setPrinciples(List<InventionPrinciple> principles) {
        this.principles = principles;
        this.selectedId = null;
        notifyDataSetChanged();
    }

    public void setSelectedId(Integer id) {
        this.selectedId = id;
        notifyDataSetChanged();
    }

    public Integer getSelectedId() {
        return selectedId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_principle_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        InventionPrinciple principle = principles.get(position);
        boolean isSelected = selectedId != null && selectedId == principle.getId();

        holder.tvNumber.setText(principle.getId() + "번");
        holder.tvName.setText(principle.getName());
        holder.tvDescription.setText(principle.getDescription());

        // 선택 상태 표시
        if (isSelected) {
            holder.cardView.setStrokeWidth(4);
            holder.cardView.setStrokeColor(holder.itemView.getContext().getColor(android.R.color.holo_blue_dark));
            holder.ivCheckmark.setVisibility(View.VISIBLE);
            holder.cardView.setAlpha(1.0f);
            holder.tvNumber.setAlpha(1.0f);
            holder.tvName.setAlpha(1.0f);
            holder.tvDescription.setAlpha(1.0f);
        } else {
            holder.cardView.setStrokeWidth(1);
            holder.ivCheckmark.setVisibility(View.GONE);

            // 다른 카드가 선택되어 있으면 회색으로 표시
            if (selectedId != null) {
                holder.cardView.setAlpha(0.4f);
                holder.tvNumber.setAlpha(0.5f);
                holder.tvName.setAlpha(0.5f);
                holder.tvDescription.setAlpha(0.5f);
            } else {
                holder.cardView.setAlpha(1.0f);
                holder.tvNumber.setAlpha(1.0f);
                holder.tvName.setAlpha(1.0f);
                holder.tvDescription.setAlpha(1.0f);
            }
        }

        holder.cardView.setOnClickListener(v -> {
            int previousSelectedId = selectedId != null ? selectedId : -1;

            // 같은 카드를 다시 클릭하면 선택 해제
            if (selectedId != null && selectedId == principle.getId()) {
                selectedId = null;
            } else {
                selectedId = principle.getId();
            }

            notifyDataSetChanged();

            if (listener != null) {
                listener.onPrincipleSelected(selectedId);
            }
        });
    }

    @Override
    public int getItemCount() {
        return principles.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView cardView;
        TextView tvNumber;
        TextView tvName;
        TextView tvDescription;
        ImageView ivCheckmark;

        ViewHolder(View itemView) {
            super(itemView);
            cardView = (MaterialCardView) itemView;
            tvNumber = itemView.findViewById(R.id.tvPrincipleNumber);
            tvName = itemView.findViewById(R.id.tvPrincipleName);
            tvDescription = itemView.findViewById(R.id.tvPrincipleDescription);
            ivCheckmark = itemView.findViewById(R.id.ivCheckmark);
        }
    }
}
