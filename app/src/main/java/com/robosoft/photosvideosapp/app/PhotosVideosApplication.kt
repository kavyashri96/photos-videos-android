package com.robosoft.photosvideosapp.app

import android.app.Application
import android.content.Context
import com.robosoft.photosvideosapp.di.module.repoModule
import com.robosoft.photosvideosapp.di.module.viewModelModule
import com.robosoft.photosvideosapp.di.module.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PhotosVideosApp : Application() {

    companion object {
        private lateinit var appContext: Context

        @JvmStatic
        fun getContext(): Context {
            return appContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
        startKoin {
            androidContext(this@PhotosVideosApp)
            modules(listOf(appModule, repoModule, viewModelModule))
        }
    }
}