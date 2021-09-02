package com.example.weatherapp.model.database

import androidx.room.*

@Dao
interface HistoryDao {
    @Query("SELECT * FROM HistoryEntity")
    fun all(): List<HistoryEntity>

    @Query("SELECT * FROM HistoryEntity WHERE city = :city")
    fun getDataByWord(city: String): List<HistoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: HistoryEntity)

    @Update
    fun update(entity: HistoryEntity)

    @Delete
    fun delete(entity: HistoryEntity)

    @Query("DELETE FROM HistoryEntity WHERE city = :cityName")
    fun deleteByCityName(cityName: String)
}