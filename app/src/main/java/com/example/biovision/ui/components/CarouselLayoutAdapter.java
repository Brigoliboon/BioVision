package com.example.biovision.ui.components;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.biovision.R;
import com.example.biovision.data.API.Plant.model.Plant;
import com.example.biovision.data.API.Plant.model.PlantResult;

import java.util.List;

public class CarouselLayoutAdapter extends RecyclerView.Adapter<CarouselLayoutAdapter.ViewHolder> {

    private final List<Integer> layoutIds; // Each item = layout file
    private  final Plant result;

    public CarouselLayoutAdapter(List<Integer> layoutIds, Plant result) {
        this.layoutIds = layoutIds;
        this.result = result;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(layoutIds.get(viewType), parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return layoutIds.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position; // So each layout can be different
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Bind data to views based on position
        View view = holder.itemView;
        if (position == 0) {
            TextView textView = view.findViewById(R.id.description);
            textView.setText(result.detail().description());
        } else if (position == 1) {
        }
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
