package com.example.tripplanner.members.di

import com.example.kotlinbaseapplication.di.AppModule
import com.example.tripplanner.members.view.AddMembersActivity
import com.example.tripplanner.members.view.MembersListActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MembersModule::class, AppModule::class])
interface MembersComponent {
  fun inject(activity: AddMembersActivity)
  fun inject(activity: MembersListActivity)
}