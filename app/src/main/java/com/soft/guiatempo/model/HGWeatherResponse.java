package com.soft.guiatempo.model;

import java.time.LocalDate;

public class HGWeatherResponse {

    private String temp;
    private LocalDate date;
    private String description;
    private String cityName;
    private HGWeatherForecast[] forecast;

    public class HGWeatherForecast {

        private LocalDate date;
        private int max;
        private int min;
        private String description;

    }

}

/**
 * https://api.hgbrasil.com/weather?array_limit=5&fields=only_results,temp,city_name,forecast,max,min,date,description&key=ee732a7e&city_name=Campinas,SP
 */

/**
 * {"temp":20,
 * "date":"04/02/2020",
 * "description":"Tempo nublado",
 * "city_name":"Sao Paulo",
 * "forecast":[
 * {"date":"04/02","max":25,"min":19,"description":"Tempestades isoladas"},
 * {"date":"05/02","max":26,"min":19,"description":"Tempestades"},
 * {"date":"06/02","max":26,"min":19,"description":"Tempestades"},
 * {"date":"07/02","max":26,"min":20,"description":"Tempestades"},
 * {"date":"08/02","max":27,"min":18,"description":"Tempestades"}]}
 */
