package com.example.data.mappers

import com.example.data.common.DataMapper
import com.example.data.entities.MemberEntity
import com.example.data.entities.TripEntity
import com.example.domain.models.Member
import com.example.domain.models.Trip
import javax.inject.Inject

class MembersModelToEntityMapper @Inject constructor() : DataMapper<Member, MemberEntity> {
    override fun transform(data: Member): MemberEntity {
        val entity = MemberEntity()
        data?.let { member ->
            entity.name = member.name
            entity.contribution = member.contribution
            entity.expense = member.expanse
            entity.number = member.number
            entity.trip_id = member.tripId
        }
        return entity
    }

}