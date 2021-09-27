package com.example.bravedevelopers.di


import com.example.bravedevelopers.presentation.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [Module::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}
