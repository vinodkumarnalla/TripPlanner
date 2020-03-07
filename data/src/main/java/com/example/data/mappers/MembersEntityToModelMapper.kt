package com.example.data.mappers

import com.example.data.common.DataMapper
import com.example.data.entities.MemberEntity
import com.example.data.entities.TripEntity
import com.example.domain.models.Member
import com.example.domain.models.Trip
import javax.inject.Inject

class MembersEntityToModelMapper @Inject constructor() :
    DataMapper<List<MemberEntity>, List<Member>> {
    override fun transform(data: List<MemberEntity>): List<Member> {
        val result = ArrayList<Member>()
        data?.let {
            for (item in data) {
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
        return result
    }

}