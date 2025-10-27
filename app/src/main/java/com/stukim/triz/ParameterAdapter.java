package com.stukim.triz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ParameterAdapter extends RecyclerView.Adapter<ParameterAdapter.ViewHolder> {

    private List<TrizParameter> parameters = new ArrayList<>();

    public void setParameters(List<TrizParameter> parameters) {
        this.parameters = parameters;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_parameter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TrizParameter parameter = parameters.get(position);
        holder.tvName.setText(parameter.toString());
    }

    @Override
    public int getItemCount() {
        return parameters.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvParameterName);
        }
    }
}
