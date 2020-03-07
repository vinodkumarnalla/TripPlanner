package com.example.data.mappers

import com.example.data.common.DataMapper
import com.example.data.entities.TripEntity
import com.example.domain.models.Trip
import javax.inject.Inject

class TripsDbToModelMapper @Inject constructor() : DataMapper<List<TripEntity>, List<Trip>> {
    override fun transform(data: List<TripEntity>): List<Trip> {
        val result = ArrayList<Trip>()
        data?.let {
            for (item in data) {
                result.add(Trip(item.name ?: "", item.id?:1))
            }
        }
        return result
    }

}