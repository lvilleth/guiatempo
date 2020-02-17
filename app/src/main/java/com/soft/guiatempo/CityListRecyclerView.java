package com.soft.guiatempo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soft.guiatempo.model.entity.CityEntity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

public class CityListRecyclerView extends RecyclerView.Adapter<CityListRecyclerView.CityViewHolder> {

    private List<CityEntity> cachedCities; // Cached copy of words
    private Application application;
    private  CityViewModel cityViewModel;

    class CityViewHolder extends RecyclerView.ViewHolder {

        private final TextView wordItemView;


        private CityViewHolder(View itemView) {
            super(itemView);

            wordItemView = itemView.findViewById(R.id.textView);

            wordItemView.setOnClickListener( x -> {
                Intent intent = new Intent(itemView.getContext(), cityClimate.class);
                intent.putExtra("city", wordItemView.getText());
                itemView.getContext().startActivity( intent );
            });


            wordItemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    cityViewModel.deleteByName(wordItemView.getText().toString());
                    return true;
                }
            });
        }
    }

    public CityListRecyclerView(Activity context, CityViewModel cityViewModel){
        this.application = context.getApplication();
        this.cityViewModel = cityViewModel;

    }

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
