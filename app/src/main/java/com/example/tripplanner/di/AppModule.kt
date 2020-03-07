package com.example.kotlinbaseapplication.di

import android.content.Context
import androidx.room.Room
import com.example.data.daos.MembersDao
import com.example.data.daos.TripDao
import com.example.data.database.TripsDataBase
import com.example.data.mappers.MembersEntityToModelMapper
import com.example.data.mappers.MembersModelToEntityMapper
import com.example.data.mappers.TripsDbToModelMapper
import com.example.data.repository.TripRepoImplementation
import com.example.domain.repository.TripRepository
import com.example.tripplanner.MyApplication
import com.example.tripplanner.utils.RxBus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class])
class AppModule(private val application: MyApplication) {

    @Provides
    @Singleton
    fun getApplicationContext(): Context {
        return application.applicationContext
    }


    @Singleton
    @Provides
    fun provideDatabase(context: Context): TripsDataBase {
        return Room.databaseBuilder(
            context,
            TripsDataBase::class.java, TripsDataBase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun provideTripDao(dataBase: TripsDataBase): TripDao {
        return dataBase.getTripDao()
    }

    @Singleton
    @Provides
    fun provideMembersDao(dataBase: TripsDataBase): MembersDao {
        return dataBase.getMembersDao()
    }

    @Provides
    @Singleton
    fun getTripPlannerRepo(
        dao: TripDao,
        membersDao: MembersDao,
        mapper: TripsDbToModelMapper,
        membersEntityToModelMapper: MembersEntityToModelMapper,
        membersModelToEntityMapper: MembersModelToEntityMapper
    ): TripRepository {
        return TripRepoImplementation(
            dao,
            membersDao,
            mapper,
            membersModelToEntityMapper,
            membersEntityToModelMapper
        )
    }

    @Provides
    @Singleton
    fun provideRxBus(): RxBus {
        return RxBus()
    }


}