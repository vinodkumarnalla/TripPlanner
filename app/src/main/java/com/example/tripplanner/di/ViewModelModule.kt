package com.example.kotlinbaseapplication.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinbaseapplication.utils.MyViewModelFactory
import com.example.kotlinbaseapplication.utils.ViewModelKey
import com.example.tripplanner.members.viewmodel.MembersViewModel
import com.example.tripplanner.trip.viewmodel.TripViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TripViewModel::class)
    abstract fun bindAddTripViewModel(viewModel: TripViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MembersViewModel::class)
    abstract fun bindAddMembersViewModel(viewModel: MembersViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: MyViewModelFactory): ViewModelProvider.Factory
}