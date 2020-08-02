package com.lib.remotelogger.logutility

import android.app.Application
import android.os.Build
import android.provider.Settings
import android.util.Log
import com.lib.remotelogger.BuildConfig
import com.lib.remotelogger.utility.toDate
import com.lib.remotelogger.utility.toDateString

class LogFormatter(application: Application?) {

    val deviceUUID =
    Settings.Secure.getString(application?.contentResolver, Settings.Secure.ANDROID_ID)

    fun formatLog(
        tag: String,
        message: String,
        throwable: Throwable? = null,
        logLevel: Int
    ): String {
        val currentTimeMillis = System.currentTimeMillis()
        val currentDate = currentTimeMillis.toDate()

        val timeStamp = currentDate.toDateString(toFormat = "yyyy-MM-dd hh:mm:ss")
        val appVersion = "${BuildConfig.VERSION_NAME}-${BuildConfig.VERSION_CODE}"
        val osVersion = "Android-" + Build.VERSION.RELEASE
        val logLevelName = getLogLevelName(messageLogLevel = logLevel)

        val finalMessage = if (throwable != null) {
            "$message $throwable"
        } else {
            message
        }
        val logFormattedText =
            "$timeStamp | $appVersion : $osVersion | ${deviceUUID} | [$logLevelName/$tag]: $finalMessage\n"
        return logFormattedText
    }

    fun formatCrashLog(
        message: String
    ): String {
        val currentTimeMillis = System.currentTimeMillis()
        val currentDate = currentTimeMillis.toDate()

        val timeStamp = currentDate.toDateString(toFormat = "yyyy-MM-dd hh:mm:ss")
        val appVersion = "${BuildConfig.VERSION_NAME}-${BuildConfig.VERSION_CODE}"
        val osVersion = "Android-" + Build.VERSION.RELEASE

        val logFormattedText =
            "$timeStamp | $appVersion : $osVersion | ${deviceUUID} | $message\n"
        return logFormattedText
    }

    private fun getLogLevelName(messageLogLevel: Int): String? {
        return when (messageLogLevel) {
            Log.VERBOSE -> "VERBOSE"
            Log.DEBUG -> "DEBUG"
            Log.INFO -> "INFO"
            Log.WARN -> "WARN"
            Log.ERROR -> "ERROR"
            Log.ASSERT -> "ASSERT"
            else -> "NONE"
        }
    }
}