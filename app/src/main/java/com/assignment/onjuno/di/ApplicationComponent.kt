package com.assignment.onjuno.di

import com.assignment.onjuno.HomeActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {

    fun inject(homeActivity: HomeActivity)
}