package com.soft.guiatempo.db.repository;

import android.app.Application;

import com.soft.guiatempo.db.CityRoomDatabase;
import com.soft.guiatempo.db.dao.CityDAO;
import com.soft.guiatempo.model.entity.CityEntity;

import java.util.List;

import androidx.lifecycle.LiveData;

public class CityRepository {

    private CityDAO cityDAO;

    private LiveData<List<CityEntity>> allCities;


    public CityRepository(Application application) {
        CityRoomDatabase db = CityRoomDatabase.getDatabase(application);
        cityDAO = db.cityDAO();
        allCities = cityDAO.findAll();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<CityEntity>> findAllCities(){
        return allCities;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void save(final CityEntity... city) {
        CityRoomDatabase.databaseWriteExecutor.execute(() -> {
            cityDAO.save(city);
        });
    }

    public CityEntity findCity(Long id){
        return cityDAO.findCityById(id);
    }

    public LiveData<List<CityEntity>> findCityByName(String name){
        return cityDAO.findCityByNameLike(name);
    }

    public void delete(final CityEntity... city){
        CityRoomDatabase.databaseWriteExecutor.execute(() -> {
            cityDAO.deleteCity(city);
        });
    }

    public void deleteByName(final String cityName){
        CityRoomDatabase.databaseWriteExecutor.execute(() -> {
            cityDAO.deleteCityByName(cityName);
        });
    }

}
