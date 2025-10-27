package com.example.triz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PrincipleAdapter extends RecyclerView.Adapter<PrincipleAdapter.ViewHolder> {

    private List<InventionPrinciple> principles = new ArrayList<>();

    public void setPrinciples(List<InventionPrinciple> principles) {
        this.principles = principles;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_principle, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        InventionPrinciple principle = principles.get(position);
        holder.tvName.setText(principle.getId() + ". " + principle.getName());
        holder.tvDescription.setText(principle.getDescription());
        holder.tvExamples.setText(principle.getExamples());
    }

    @Override
    public int getItemCount() {
        return principles.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvDescription;
        TextView tvExamples;
        EditText etMemo;

        ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvPrincipleName);
            tvDescription = itemView.findViewById(R.id.tvPrincipleDescription);
            tvExamples = itemView.findViewById(R.id.tvPrincipleExamples);
            etMemo = itemView.findViewById(R.id.etMemo);
        }
    }
}
