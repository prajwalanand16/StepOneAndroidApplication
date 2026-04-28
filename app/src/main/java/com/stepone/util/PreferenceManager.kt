package com.stepone.util

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceManager @Inject constructor(@ApplicationContext context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("stepone_prefs", Context.MODE_PRIVATE)

    fun isPro(): Boolean {
        return prefs.getBoolean("is_pro", false)
    }

    fun setPro(isPro: Boolean) {
        prefs.edit().putBoolean("is_pro", isPro).apply()
    }
}
