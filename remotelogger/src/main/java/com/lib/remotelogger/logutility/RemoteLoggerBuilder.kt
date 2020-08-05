package com.lib.remotelogger.logutility

import android.app.Application
import com.lib.remotelogger.utility.RemoteLogger

class RemoteLoggerBuilder {
    private var application: Application? = null
    private var logFileNamePrefix: String? = null
    private var crashLogsEnabled: Boolean = false

    fun getApplication() = application
    fun getLogFileNamePrefix() = logFileNamePrefix
    fun getCrashLogsEnabled() = crashLogsEnabled

    fun setApplication(application: Application): RemoteLoggerBuilder {
        this.application = application
        return this
    }

    fun setLogFileNamePrefix(logFileNamePrefix: String): RemoteLoggerBuilder {
        this.logFileNamePrefix = logFileNamePrefix
        return this
    }

    fun setCrashLogsEnabled(crashLogsEnabled: Boolean): RemoteLoggerBuilder {
        this.crashLogsEnabled = crashLogsEnabled
        return this
    }

    fun build() {
        isValidateRemoteLogger()
        RemoteLogger(this)
    }

    private fun isValidateRemoteLogger(): Boolean {
        if (application == null) return false
        return true
    }
}