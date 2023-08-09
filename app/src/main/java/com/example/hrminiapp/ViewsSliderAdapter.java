package com.example.hrminiapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewsSliderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    int[] layoutScreens;
    public ViewsSliderAdapter(int[] layouts){
        layoutScreens = layouts;
    }

    @NonNull
    @Override
    // called each time screen is swiped.
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(viewType, parent, false);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    // get count of slide view menu
    @Override
    public int getItemCount() {
        return layoutScreens.length;
    }

    @Override
    public int getItemViewType(int position) {
        return layoutScreens[position];
    }

    // Using recycler view to show slider menus
    public class SliderViewHolder extends RecyclerView.ViewHolder {
        public SliderViewHolder(View view) {
            super(view);
        }
    }
}
