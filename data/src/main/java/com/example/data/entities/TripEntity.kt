package com.example.data.entities

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trips")
class TripEntity {
    @PrimaryKey
    var id: Long? = null

    @Nullable
    var name: String? = null
}