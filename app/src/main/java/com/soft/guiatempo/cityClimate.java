package com.soft.guiatempo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.Response;
import com.soft.guiatempo.model.entity.CityEntity;

import org.json.JSONArray;
import org.json.JSONObject;


public class cityClimate extends AppCompatActivity {

    private String cityName;
    private ImageView btn_back;

    private TextView txt_city;
    private TextView txt_temp;
    private TextView txt_clima_00;

    private TextView txt_max_00;
    private TextView txt_min_00;
    private TextView txt_max_01;
    private TextView txt_min_01;
    private TextView txt_max_02;
    private TextView txt_min_02;
    private TextView txt_max_03;
    private TextView txt_min_03;
    private TextView txt_max_04;
    private TextView txt_min_04;

    private TextView txt_day_01;
    private TextView txt_day_02;
    private TextView txt_day_03;
    private TextView txt_day_04;

    private ImageView img_00;
    private ImageView img_01;
    private ImageView img_02;
    private ImageView img_03;
    private ImageView img_04;

    private  CityViewModel cityViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_climate);

        cityViewModel = new ViewModelProvider(this).get(CityViewModel.class);

        /*********************************/
        /** capturing the selected city **/
        /*********************************/
        Bundle params = getIntent().getExtras();

        if(params != null) {
            cityName = params.getString("city");
        }
        /*********************************/
        /** capturing the selected city **/
        /*********************************/

        // Elements
        btn_back = findViewById(R.id.btn_back);
        txt_city = findViewById(R.id.txt_city);
        txt_temp = findViewById(R.id.txt_temp);
        txt_clima_00 = findViewById(R.id.txt_clima_00);

        txt_max_00 = findViewById(R.id.txt_max_00);
        txt_max_01 = findViewById(R.id.txt_max_02);
        txt_max_02 = findViewById(R.id.txt_max_04);
        txt_max_03 = findViewById(R.id.txt_max_01);
        txt_max_04 = findViewById(R.id.txt_max_03);

        txt_min_00 = findViewById(R.id.txt_min_00);
        txt_min_01 = findViewById(R.id.txt_min_02);
        txt_min_02 = findViewById(R.id.txt_min_04);
        txt_min_03 = findViewById(R.id.txt_min_01);
        txt_min_04 = findViewById(R.id.txt_min_03);

        txt_day_01 = findViewById(R.id.txt_day_02);
        txt_day_02 = findViewById(R.id.txt_day_04);
        txt_day_03 = findViewById(R.id.txt_day_01);
        txt_day_04 = findViewById(R.id.txt_day_03);

        img_00 = findViewById(R.id.img_00);
        img_01 = findViewById(R.id.img_02);
        img_02 = findViewById(R.id.img_04);
        img_03 = findViewById(R.id.img_01);
        img_04 = findViewById(R.id.img_03);


        /********************************/
        /** Requisição para o servidor **/
        /********************************/
        RequestQueue requestQueue;

        // Instantiate the cache
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

        // Instantiate the RequestQueue with the cache and network.
        requestQueue = new RequestQueue(cache, network);

        // Start the queue
        requestQueue.start();

        if(cityName == null){
            cityName = "joao pessoa";
        }

        String url ="https://api.hgbrasil.com/weather?array_limit=5&fields=only_results,temp,city_name,forecast,max,min,date,condition,description&key=ee732a7e&city_name=" + cityName;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("Response: " + response.toString());

                        cityViewModel.findCityByName(cityName).observe(cityClimate.this, listCities -> {
                            CityEntity cityEntity;
                            if(listCities != null && listCities.size() > 0){
                                // set Data
                                cityEntity = listCities.get(0);
                                // saveEntity
                                cityEntity.setData(response.toString());
                                cityViewModel.save(cityEntity);
                            }
                        });

                        dataProcessing( response.toString() );
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Falhou ao capturar elemento!!");

                        cityViewModel.findCityByName(cityName).observe(cityClimate.this, listCities -> {
                            CityEntity cityEntity;
                            if(listCities != null && listCities.size() > 0){
                                cityEntity = listCities.get(0);

                                dataProcessing( cityEntity.getData() );
                            }
                        });

                    }
                });

        // Add the request to the RequestQueue.
        requestQueue.add(jsonObjectRequest);
        /********************************/
        /** Requisição para o servidor **/
        /********************************/

        // to back
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( cityClimate.this, ListCities.class));
            }
        });

    }

    // returns the image value according to the climatic condition
    public int imgProcessing(String condition) {
        switch (condition) {
            case "storm":
                return R.drawable.storm;
            case "snow":
                return R.drawable.snow;
            case "hail":
                return R.drawable.hail;
            case "rain":
                return R.drawable.rain;
            case "fog":
                return R.drawable.fog;
            case "clear_day":
                return R.drawable.clear_day;
            case "clear_night":
                return R.drawable.clear_night;
            case "cloud":
                return R.drawable.cloud;
            case "cloudly_day":
                return R.drawable.cloudly_day;
            case "cloudly_night":
                return R.drawable.cloudly_night;
            case "none_day":
                return R.drawable.clear_day;
            case "none_night":
                return R.drawable.clear_night;
            default:
                return 0;
        }
    }

    // processes the data and inserts it into the interface
    public void dataProcessing( String res) {
        try {
            // transform string into JSONObject
            JSONObject response = new JSONObject(res);

            // working with array
            JSONArray forecast = response.getJSONArray("forecast");

            int i;
            for(i = 0; i < forecast.length(); i++) {
                JSONObject climate = forecast.getJSONObject(i);
                System.out.println(climate);

                int codImg = imgProcessing(climate.getString("condition"));

                switch (i) {
                    case 0:
                        img_00.setImageResource(codImg);
                        txt_city.setText( response.getString("city_name") );
                        txt_temp.setText( response.getString("temp") + "ºC");
                        txt_clima_00.setText( response.getString("description") );
                        txt_max_00.setText(climate.getString("max") + "ºC");
                        txt_min_00.setText(climate.getString("min") + "ºC");
                        break;

                    case 1:
                        img_01.setImageResource(codImg);
                        txt_day_01.setText(climate.getString("date"));
                        txt_max_01.setText(climate.getString("max") + "ºC");
                        txt_min_01.setText(climate.getString("min") + "ºC");
                        break;

                    case 2:
                        img_02.setImageResource(codImg);
                        txt_day_02.setText(climate.getString("date"));
                        txt_max_02.setText(climate.getString("max") + "ºC");
                        txt_min_02.setText(climate.getString("min") + "ºC");
                        break;

                    case 3:
                        img_03.setImageResource(codImg);
                        txt_day_03.setText(climate.getString("date"));
                        txt_max_03.setText(climate.getString("max") + "ºC");
                        txt_min_03.setText(climate.getString("min") + "ºC");
                        break;

                    case 4:
                        img_04.setImageResource(codImg);
                        txt_day_04.setText(climate.getString("date"));
                        txt_max_04.setText(climate.getString("max") + "ºC");
                        txt_min_04.setText(climate.getString("min") + "ºC");
                        break;
                }
            }
        }catch(Exception e) {
            System.out.println("Error - " + e);
            startActivity(new Intent( cityClimate.this, NoData.class));
        }
    }


}
