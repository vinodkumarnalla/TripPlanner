package com.example.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.entities.TripEntity
import io.reactivex.Single


@Dao
interface TripDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(trip: TripEntity): Long

    @Query("SELECT * FROM trips")
    fun getTrips(): List<TripEntity>


}