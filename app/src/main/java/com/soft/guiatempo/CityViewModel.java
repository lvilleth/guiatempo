package com.soft.guiatempo;

import android.app.Application;

import com.soft.guiatempo.db.repository.CityRepository;
import com.soft.guiatempo.model.entity.CityEntity;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class CityViewModel extends AndroidViewModel {

    private CityRepository cityRepository;

    private LiveData<List<CityEntity>> allCities;

    public CityViewModel (Application application) {
        super(application);
        cityRepository = new CityRepository(application);
        allCities = cityRepository.findAllCities();
    }

    LiveData<List<CityEntity>> findAllCities() { return allCities; }

    public void save(CityEntity... city) { cityRepository.save(city); }

    public void delete(CityEntity... city) { cityRepository.delete(city); };

    public LiveData<List<CityEntity>> findCityByName(String name) {
        return cityRepository.findCityByName(name);

    }

    public void deleteByName(String cityName) { cityRepository.deleteByName(cityName); };

}
