package com.stepone.util

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppOpenAdManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val preferenceManager: PreferenceManager
) : Application.ActivityLifecycleCallbacks, DefaultLifecycleObserver {

    private var appOpenAd: AppOpenAd? = null
    private var isLoadingAd = false
    private var currentActivity: Activity? = null
    private var openCount = 0

    // Production Ad Unit ID
    private val AD_UNIT_ID = "ca-app-pub-7818602725323272/7845263246"

    fun registerActivityLifecycleCallbacks(application: Application) {
        application.registerActivityLifecycleCallbacks(this)
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        loadAd() // Proactively load the first ad
    }

    private fun loadAd() {
        if (isLoadingAd || isAdAvailable() || preferenceManager.isPro()) return

        isLoadingAd = true
        val request = AdRequest.Builder().build()
        AppOpenAd.load(
            context,
            AD_UNIT_ID,
            request,
            object : AppOpenAd.AppOpenAdLoadCallback() {
                override fun onAdLoaded(ad: AppOpenAd) {
                    appOpenAd = ad
                    isLoadingAd = false
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    isLoadingAd = false
                }
            }
        )
    }

    private fun isAdAvailable(): Boolean {
        return appOpenAd != null
    }

    private fun showAdIfAvailable(activity: Activity) {
        if (preferenceManager.isPro()) return

        if (!isAdAvailable()) {
            loadAd()
            return
        }

        appOpenAd?.show(activity)
        appOpenAd = null
        loadAd()
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        openCount++
        
        // Show on first open (count 1) or every 3rd time (4, 7, 10...)
        if (openCount == 1 || (openCount > 1 && (openCount - 1) % 3 == 0)) {
            currentActivity?.let { showAdIfAvailable(it) }
        }
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
    override fun onActivityStarted(activity: Activity) {
        currentActivity = activity
    }
    override fun onActivityResumed(activity: Activity) {
        currentActivity = activity
    }
    override fun onActivityPaused(activity: Activity) {}
    override fun onActivityStopped(activity: Activity) {}
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
    override fun onActivityDestroyed(activity: Activity) {
        currentActivity = null
    }
}
