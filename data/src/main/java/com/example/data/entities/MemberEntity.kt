package com.example.data.entities

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "members")
class MemberEntity {
    @PrimaryKey
    var id: Long? = null

    @Nullable
    var name: String? = null
    var number: Long = 0
    var expense: Long = 0
    var contribution: Long = 0
    var trip_id: Long = 0

}