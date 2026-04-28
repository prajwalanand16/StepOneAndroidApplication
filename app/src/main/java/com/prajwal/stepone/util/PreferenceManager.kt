package com.prajwal.stepone.util

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceManager @Inject constructor(@ApplicationContext context: Context) {
    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val prefs: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        "stepone_secure_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun isPro(): Boolean {
        return prefs.getBoolean("is_pro", false)
    }

    fun setPro(isPro: Boolean) {
        prefs.edit().putBoolean("is_pro", isPro).apply()
    }

    fun getStepGoal(): Int {
        return prefs.getInt("step_goal", 10000)
    }

    fun setStepGoal(goal: Int) {
        prefs.edit().putInt("step_goal", goal).apply()
    }
}
