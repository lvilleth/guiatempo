package com.soft.guiatempo.model;

import java.time.LocalDate;

public class HGWeatherResponse {

    private String temp;
    private LocalDate date;
    private String description;
    private String cityName;
    private Forecast[] forecast;

    public class Forecast {

        private LocalDate date;
        private int max;
        private int min;
        private String description;

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Forecast[] getForecast() {
        return forecast;
    }

    public void setForecast(Forecast[] forecast) {
        this.forecast = forecast;
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
