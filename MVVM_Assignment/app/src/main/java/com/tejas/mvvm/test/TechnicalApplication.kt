package com.tejas.mvvm.test

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TechnicalApplication: Application(){

    override fun onCreate() {
        super.onCreate()
    }

}