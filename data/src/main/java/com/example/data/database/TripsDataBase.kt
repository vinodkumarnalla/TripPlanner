package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.daos.MembersDao
import com.example.data.daos.TripDao
import com.example.data.entities.MemberEntity
import com.example.data.entities.TripEntity

@Database(version = 1, entities = [TripEntity::class, MemberEntity::class])
abstract class TripsDataBase : RoomDatabase() {

    companion object{
        val DATABASE_NAME ="trips_db"
    }

    abstract fun getTripDao(): TripDao

    abstract fun getMembersDao(): MembersDao

}