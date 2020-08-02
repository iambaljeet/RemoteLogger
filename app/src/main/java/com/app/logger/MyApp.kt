package com.app.logger

import android.app.Application
import com.lib.remotelogger.logutility.RemoteLoggerBuilder

private const val TAG = "MyApp"
class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()

        RemoteLoggerBuilder()
            .setApplication(this)
            .setLogFileNamePrefix("test")
            .setCrashLogsEnabled(true)
            .build()
    }
}