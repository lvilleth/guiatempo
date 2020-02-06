package com.soft.guiatempo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.soft.guiatempo.model.entity.CityEntity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CityViewModel cityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final CityListRecyclerView adapter = new CityListRecyclerView(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cityViewModel = new ViewModelProvider(this).get(CityViewModel.class);
        cityViewModel.findAllCities().observe(this, new Observer<List<CityEntity>>() {
            @Override
            public void onChanged(@Nullable final List<CityEntity> cities) {
                // Update the cached copy of the words in the adapter.
                adapter.setCities(cities);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("CLICK !!!!!");

                cityViewModel.save(new CityEntity("Nova_" + System.currentTimeMillis()));
            }
        });
    }


}
