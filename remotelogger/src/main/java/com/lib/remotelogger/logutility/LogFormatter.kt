package com.lib.remotelogger.logutility

import android.app.Application
import android.content.Context
import android.content.pm.PackageInfo
import android.os.Build
import android.util.Log
import com.lib.remotelogger.utility.toDate
import com.lib.remotelogger.utility.toDateString

class LogFormatter(application: Application?) {

    private var context: Context? = application?.applicationContext
    private val packageManager = context?.packageManager
    private val packageName: String? = context?.packageName
    private val packageInfo: PackageInfo? = packageManager?.getPackageInfo(packageName.toString(), 0)
    private val appVersion = if (packageInfo != null) {
        "${packageInfo.versionName}-${packageInfo.versionCode}"
    } else {
        ""
    }
    private val osVersion = "Android-" + Build.VERSION.RELEASE

    fun formatLog(
        tag: String,
        message: String,
        throwable: Throwable? = null,
        logLevel: Int
    ): String {
        val currentTimeMillis = System.currentTimeMillis()
        val currentDate = currentTimeMillis.toDate()
        val timeStamp = currentDate.toDateString(toFormat = "yyyy-MM-dd hh:mm:ss")

        val logLevelName = getLogLevelName(messageLogLevel = logLevel)

        val finalMessage = if (throwable != null) {
            "$message $throwable"
        } else {
            message
        }
        val logFormattedText =
            "$timeStamp | $appVersion : $osVersion | [$logLevelName/$tag]: $finalMessage\n"
        return logFormattedText
    }

    fun formatCrashLog(
        message: String
    ): String {
        val currentTimeMillis = System.currentTimeMillis()
        val currentDate = currentTimeMillis.toDate()
        val timeStamp = currentDate.toDateString(toFormat = "yyyy-MM-dd hh:mm:ss")

        val logFormattedText =
            "$timeStamp | $appVersion : $osVersion | $message\n"
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