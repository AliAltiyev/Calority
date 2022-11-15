package com.altyyev.calority

import android.app.Application
import com.orhanobut.hawk.Hawk
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        setUpTimber()
        setUpHawk()

    }

    private fun setUpTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree());
        }
    }

    private fun setUpHawk() {
        Hawk.init(this).build()
    }
}