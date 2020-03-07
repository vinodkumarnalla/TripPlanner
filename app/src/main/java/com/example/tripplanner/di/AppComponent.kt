package com.example.kotlinbaseapplication.di

import com.example.tripplanner.MyApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface  AppComponent {
    fun inject(myApplication: MyApplication)
}