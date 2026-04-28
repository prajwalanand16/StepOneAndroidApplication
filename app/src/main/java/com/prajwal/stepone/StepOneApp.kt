package com.prajwal.stepone

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.prajwal.stepone.util.AppOpenAdManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class StepOneApp : Application() {

    @Inject
    lateinit var appOpenAdManager: AppOpenAdManager

    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this) {}
        appOpenAdManager.registerActivityLifecycleCallbacks(this)
    }
}
