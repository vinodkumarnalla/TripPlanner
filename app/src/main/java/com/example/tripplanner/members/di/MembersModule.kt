package com.example.tripplanner.members.di

import com.example.domain.repository.TripRepository
import com.example.domain.usecases.AddMemberUseCase
import com.example.domain.usecases.AddTripUseCase
import com.example.domain.usecases.GetMembersUseCase
import com.example.domain.usecases.GetTripsUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MembersModule {


    @Provides
    @Singleton
    fun getAddMemberUseCase(repo: TripRepository): AddMemberUseCase {
        return AddMemberUseCase(repo)
    }

    @Provides
    @Singleton
    fun getMembersUseCase(repo: TripRepository): GetMembersUseCase {
        return GetMembersUseCase(repo)
    }
}