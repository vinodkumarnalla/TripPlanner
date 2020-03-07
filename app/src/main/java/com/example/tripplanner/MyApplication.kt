package com.example.tripplanner

import android.app.Application
import androidx.room.Room
import com.example.data.database.TripsDataBase
import com.example.kotlinbaseapplication.di.AppComponent
import com.example.kotlinbaseapplication.di.AppModule
import com.example.kotlinbaseapplication.di.DaggerAppComponent


class MyApplication: Application(){
    var component: AppComponent? = null
    var appModule: AppModule? = null

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        component?.inject(this)
        appModule = AppModule(this)
    }

    fun getAppComponent(): AppComponent {
        return  component!!
    }

    fun getAppModuleData(): AppModule{
        return appModule!!
    }
}