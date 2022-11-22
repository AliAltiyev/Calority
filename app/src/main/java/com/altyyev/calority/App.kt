package com.altyyev.calority

import android.app.Application
import com.orhanobut.hawk.BuildConfig
import com.orhanobut.hawk.Hawk
import com.vanniktech.emoji.EmojiManager
import com.vanniktech.emoji.google.GoogleEmojiProvider
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        setUpTimber()
        setUpHawk()
        setUpEmoji()

    }


    private fun setUpEmoji() {
        EmojiManager.install(GoogleEmojiProvider())
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