package com.kareemdev.tmdbapp

import android.app.Application
import com.kareemdev.tmdbapp.core.di.databaseModule
import com.kareemdev.tmdbapp.core.di.networkModule
import com.kareemdev.tmdbapp.core.di.repositoryModule
import com.kareemdev.tmdbapp.di.useCaseModule
import com.kareemdev.tmdbapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                )
            )
        }
    }
}