package com.example.domain.usecases

import com.example.domain.common.Result
import com.example.domain.models.Member
import com.example.domain.models.Trip
import com.example.domain.repository.TripRepository
import io.reactivex.Observable
import javax.inject.Inject

public class GetMembersUseCase @Inject constructor(private val dataRepo: TripRepository) {

    fun execute(tripId: Long): Observable<Result<List<Member>>> {
        return dataRepo.getAllMembers(tripId)
    }
}