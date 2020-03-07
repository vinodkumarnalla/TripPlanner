package com.example.tripplanner.trip.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.common.Result
import com.example.domain.models.Trip
import com.example.domain.usecases.AddTripUseCase
import com.example.domain.usecases.GetTripsUseCase
import java.lang.Error
import javax.inject.Inject

class TripViewModel @Inject constructor(
    val addTripUseCase: AddTripUseCase,
    val getTripsUseCase: GetTripsUseCase
) : ViewModel() {

    val tripAddedSuccessData = MutableLiveData<Trip>()
    val errorLiveData = MutableLiveData<Boolean>()
    val tripsLiveData = MutableLiveData<List<Trip>>()

    fun addTrip(name: String) {
        addTripUseCase.execute(name).subscribe() {
            when (it) {
                is Result.Success -> {
                    errorLiveData.value = false
                    tripAddedSuccessData.value = Trip(name, it.value)
                }
                is Result.Failure -> {
                    errorLiveData.value = true
                }
            }
        }

    }

    fun getTrips() {
        getTripsUseCase.execute().subscribe() {
            it?.let {
                when (it) {
                    is Result.Success -> {
                        errorLiveData.value = false
                        tripsLiveData.value = it.value
                    }
                    is Result.Failure -> {
                        errorLiveData.value = true
                    }
                }

            }


        }

    }

    fun getTripAddedLiveData(): LiveData<Trip> {
        return tripAddedSuccessData
    }

    fun getAllTripsLiveData(): LiveData<List<Trip>> {
        return tripsLiveData
    }

    fun getErrorLiveData(): LiveData<Boolean> {
        return errorLiveData
    }

}