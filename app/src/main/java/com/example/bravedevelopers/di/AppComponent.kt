package com.example.bravedevelopers.di


import android.app.Application
import com.example.bravedevelopers.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [Module::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}
