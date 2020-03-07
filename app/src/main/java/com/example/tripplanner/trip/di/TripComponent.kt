package com.example.tripplanner.trip.di

import com.example.kotlinbaseapplication.di.AppModule
import com.example.tripplanner.trip.view.AddTripActivity
import com.example.tripplanner.trip.view.TripsListActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TripModule::class, AppModule::class])
interface TripComponent{

    fun inject(activity: AddTripActivity)

    fun inject(activity: TripsListActivity)
}