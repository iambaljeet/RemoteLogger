package com.lib.remotelogger.logutility

import android.app.Application
import com.lib.remotelogger.utility.RemoteLogger

class RemoteLoggerBuilder {
    private var application: Application? = null
    private var uploadUrl: String? = null
    private var uploadLogOnLaunch: Boolean = false
    private var uploadLogsTimeIntervalMinutes: Long = 1
    private var logFileNamePrefix: String? = null
    private var crashLogsEnabled: Boolean = false

    fun getApplication() = application
    fun getUploadUrl() = uploadUrl
    fun getUploadLogOnLaunch() = uploadLogOnLaunch
    fun getUploadLogsTimeIntervalMinutes() = uploadLogsTimeIntervalMinutes
    fun getLogFileNamePrefix() = logFileNamePrefix
    fun getCrashLogsEnabled() = crashLogsEnabled

    fun setApplication(application: Application): RemoteLoggerBuilder {
        this.application = application
        return this
    }

    fun setUploadUrl(uploadUrl: String): RemoteLoggerBuilder {
        this.uploadUrl = uploadUrl
        return this
    }

    fun setUploadLogsTimeIntervalMinutes(uploadLogsTimeIntervalMinutes: Long): RemoteLoggerBuilder {
        this.uploadLogsTimeIntervalMinutes = uploadLogsTimeIntervalMinutes
        return this
    }

    fun setUploadLogOnLaunch(uploadLogOnLaunch: Boolean): RemoteLoggerBuilder {
        this.uploadLogOnLaunch = uploadLogOnLaunch
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
        else if (uploadLogOnLaunch && uploadUrl.isNullOrBlank()) return false
        else if (uploadLogsTimeIntervalMinutes < 1) return false
        return true
    }
}