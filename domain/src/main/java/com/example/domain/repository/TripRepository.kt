package com.example.domain.repository

import com.example.domain.common.Result
import com.example.domain.models.Member
import com.example.domain.models.Trip
import com.example.domain.models.TripDataModel
import io.reactivex.Observable

interface TripRepository {
    fun addTrip(trip: String): Observable<Result<Long>>

    fun getAllTrips(): Observable<Result<List<Trip>>>
    fun addMember(member: Member): Observable<Result<Long>>
    fun getAllMembers(tripId: Long): Observable<Result<TripDataModel>>
}