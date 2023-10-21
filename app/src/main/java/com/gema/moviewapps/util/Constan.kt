package com.gema.moviewapps.util

import androidx.datastore.preferences.core.stringPreferencesKey
import com.gema.moviewapps.BuildConfig

object Constan {
    const val TAG = "Response::::::::::::"

    const val getToken = "Bearer ${BuildConfig.API_READ_KEY}"
}