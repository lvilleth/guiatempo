package com.soft.guiatempo;

public class Employee {

    private String temp;
    private String date;
    private String description;
    private String city_name;
    private Object[] forecast;


    public Employee(String temp, String date, String description, String city_name, Object[] forecast) {
        this.temp = temp;
        this.date = date;
        this.description = description;
        this.city_name = city_name;
        this.forecast = forecast;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public Object[] getForecast() {
        return forecast;
    }

    public void setForecast(Object[] forecast) {
        this.forecast = forecast;
    }
}


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