package com.soft.guiatempo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.soft.guiatempo.model.entity.CityEntity;

public class RegisterCity extends AppCompatActivity {

    private Button btn_cadastro;
    private ImageView btn_back;
    private EditText input_city;

    private CityViewModel cityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_city);


        btn_cadastro = findViewById(R.id.btn_cadastrar);
        btn_back = findViewById(R.id.btn_back);
        input_city = findViewById(R.id.edt_city);
        cityViewModel = new ViewModelProvider(this).get(CityViewModel.class);


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterCity.this, ListCities.class);
                startActivity( intent );
            }
        });

        btn_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cityViewModel.save(new CityEntity( input_city.getText().toString() ));

                Intent intent = new Intent(RegisterCity.this, ListCities.class);
                startActivity( intent );
            }
        });
    }
}
