package com.example.domain.usecases

import com.example.domain.common.Result
import com.example.domain.models.Trip
import com.example.domain.repository.TripRepository
import io.reactivex.Observable
import javax.inject.Inject

public class AddTripUseCase @Inject constructor(private val dataRepo: TripRepository) {

    fun execute(trip: String): Observable<Result<Long>> {
        return dataRepo.addTrip(trip)
    }
}