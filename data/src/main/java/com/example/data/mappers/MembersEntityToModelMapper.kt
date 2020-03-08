package com.example.data.mappers

import com.example.data.common.DataMapper
import com.example.data.entities.MemberEntity
import com.example.data.entities.TripEntity
import com.example.domain.models.Member
import com.example.domain.models.Trip
import com.example.domain.models.TripDataModel
import javax.inject.Inject

class MembersEntityToModelMapper @Inject constructor() :
    DataMapper<List<MemberEntity>, TripDataModel> {
    override fun transform(data: List<MemberEntity>): TripDataModel {
        val result = ArrayList<Member>()
        var totalExp = 0L
        var totalContribution = 0L
        data?.let {
            for (item in data) {
                totalExp += item.expense
                totalContribution += item.contribution
                result.add(
                    Member(
                        item.id ?: 1,
                        item.name ?: "",
                        item.number,
                        item.contribution,
                        item.expense,
                        item.trip_id
                    )
                )
            }
        }

        return TripDataModel(result, totalExp, totalContribution)
    }

}