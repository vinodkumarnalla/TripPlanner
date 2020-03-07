package com.example.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.entities.MemberEntity
import com.example.data.entities.TripEntity
import io.reactivex.Single
import java.lang.reflect.Member


@Dao
interface MembersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(member: MemberEntity): Long

    @Query("SELECT * FROM members where trip_id =:tripId")
    fun getMembers(tripId: Long): List<MemberEntity>

}