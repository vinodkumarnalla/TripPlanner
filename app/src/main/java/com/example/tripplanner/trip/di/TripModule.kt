package com.example.tripplanner.trip.di

import com.example.domain.repository.TripRepository
import com.example.domain.usecases.AddTripUseCase
import com.example.domain.usecases.GetTripsUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TripModule {

    @Provides
    @Singleton
    fun getAddTripUseCase(repo: TripRepository): AddTripUseCase {
        return AddTripUseCase(repo)
    }

    @Provides
    @Singleton
    fun getTripsUseCase(repo: TripRepository): GetTripsUseCase {
        return GetTripsUseCase(repo)
    }
}