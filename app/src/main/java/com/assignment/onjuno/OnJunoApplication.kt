package com.assignment.onjuno

import android.app.Application
import com.assignment.onjuno.di.ApplicationComponent
import com.assignment.onjuno.di.DaggerApplicationComponent

class OnJunoApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder().build()
    }
}