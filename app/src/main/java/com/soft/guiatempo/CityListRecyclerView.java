package com.soft.guiatempo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soft.guiatempo.model.entity.CityEntity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CityListRecyclerView extends RecyclerView.Adapter<CityListRecyclerView.CityViewHolder> {

    private List<CityEntity> cachedCities; // Cached copy of words

    class CityViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private CityViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }

    public CityListRecyclerView(Context context){}

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.recyclerview_item, parent, false);
        return new CityViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        if (cachedCities != null) {
            CityEntity current = cachedCities.get(position);
            holder.wordItemView.setText(current.getName());
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No City");
        }
    }

    @Override
    public int getItemCount() {
        return cachedCities == null ? 0 : cachedCities.size();
    }

    public void setCities(List<CityEntity> cities){
        cachedCities = cities;
        notifyDataSetChanged();
    }
}
