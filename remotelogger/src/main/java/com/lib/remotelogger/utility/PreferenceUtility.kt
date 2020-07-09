package com.lib.remotelogger.utility

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class PreferenceUtility(application: Application) {
    val sharedPref = application.getSharedPreferences(
        application.packageName, Context.MODE_PRIVATE)
}