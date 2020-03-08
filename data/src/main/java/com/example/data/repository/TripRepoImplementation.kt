package com.example.data.repository

import com.example.data.daos.MembersDao
import com.example.data.daos.TripDao
import com.example.data.database.TripsDataBase
import com.example.data.entities.MemberEntity
import com.example.data.entities.TripEntity
import com.example.data.mappers.MembersEntityToModelMapper
import com.example.data.mappers.MembersModelToEntityMapper
import com.example.data.mappers.TripsDbToModelMapper
import com.example.domain.common.Result
import com.example.domain.models.Member
import com.example.domain.models.Trip
import com.example.domain.models.TripDataModel
import com.example.domain.repository.TripRepository
import io.reactivex.Observable
import javax.inject.Inject

class TripRepoImplementation @Inject constructor(
    val tripDao: TripDao,
    val membersDao: MembersDao,
    val tripsDbToModelMapper: TripsDbToModelMapper,
    val membersModelToEntityMapper: MembersModelToEntityMapper,
    val membersEntityToModelMapper: MembersEntityToModelMapper
) :
    TripRepository {

    override fun addTrip(trip: String): Observable<Result<Long>> {
        val entity = TripEntity()
        entity.name = trip
        return Observable.just(Result.Success(tripDao.insert(entity)) as Result<Long>)
            .onErrorReturn {
                Result.Failure(0)
            }
    }


    override fun getAllTrips(): Observable<Result<List<Trip>>> {
        val trips = tripDao.getTrips()
        return Observable.just(trips).map {
            Result.Success(tripsDbToModelMapper.transform(it)) as Result<List<Trip>>
        }.onErrorReturn {
            Result.Failure(emptyList())
        }
    }

    override fun addMember(member: Member): Observable<Result<Long>> {
        val entity = membersModelToEntityMapper.transform(member)
        return Observable.just(Result.Success(membersDao.insert(entity)) as Result<Long>)
            .onErrorReturn {
                Result.Failure(0)
            }
    }

    override fun getAllMembers(tripId: Long): Observable<Result<TripDataModel>> {
        val members = membersDao.getMembers(tripId)
        return Observable.just(members).map {
            Result.Success(membersEntityToModelMapper.transform(it)) as Result<TripDataModel>
        }.onErrorReturn {
            Result.Failure(TripDataModel(emptyList(),0,0))
        }
    }
}