package com.soft.guiatempo.db.dao;

import com.soft.guiatempo.model.entity.CityEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface CityDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(CityEntity... city);

    @Delete
    void deleteCity(CityEntity... city);

    @Query("DELETE FROM city where name like :cityName")
    void deleteCityByName(String cityName);

    @Query("SELECT * FROM city order by name asc")
    List<CityEntity> findAllSync();

    @Query("SELECT * FROM city order by name asc")
    LiveData<List<CityEntity>> findAll();

    @Query("SELECT * FROM city where id = :cityId")
    CityEntity findCityById(Long cityId);

    @Query("SELECT * FROM city where name like :cityName order by name asc")
    LiveData<List<CityEntity>> findCityByNameLike(String cityName);
}
