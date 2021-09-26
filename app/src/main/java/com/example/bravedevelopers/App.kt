package com.example.bravedevelopers

import android.app.Application
import com.example.bravedevelopers.di.AppComponent
import com.example.bravedevelopers.di.DaggerAppComponent

lateinit var appComponent: AppComponent
class App:Application() {

    override fun onCreate() {
        super.onCreate()

      appComponent = DaggerAppComponent
                .builder()
                .build()

    }



}
