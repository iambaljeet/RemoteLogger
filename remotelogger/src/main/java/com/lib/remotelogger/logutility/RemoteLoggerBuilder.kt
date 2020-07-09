package com.lib.remotelogger.logutility

import android.app.Application
import com.lib.remotelogger.utility.RemoteLogger

class RemoteLoggerBuilder {
    private var application: Application? = null
    private var uploadUrl: String? = null
    private var isRemoteLoggingEnabled: Boolean = false
    private var uploadLogOnLaunch: Boolean = false
    private var isCrashLogsEnabled: Boolean = false

    fun getApplication() = application
    fun getUploadUrl() = uploadUrl
    fun getIsRemoteLoggingEnabled() = isRemoteLoggingEnabled
    fun getUploadLogOnLaunch() = uploadLogOnLaunch
    fun getIsCrashLogsEnabled() = isCrashLogsEnabled

    fun setApplication(application: Application): RemoteLoggerBuilder {
        this.application = application
        return this
    }

    fun setUploadUrl(uploadUrl: String): RemoteLoggerBuilder {
        this.uploadUrl = uploadUrl
        return this
    }

    fun setIsRemoteLoggingEnabled(isRemoteLoggingEnabled: Boolean): RemoteLoggerBuilder {
        this.isRemoteLoggingEnabled = isRemoteLoggingEnabled
        return this
    }

    fun setUploadLogOnLaunch(uploadLogOnLaunch: Boolean): RemoteLoggerBuilder {
        this.uploadLogOnLaunch = uploadLogOnLaunch
        return this
    }

    fun setIsCrashLogsEnabled(isCrashLogsEnabled: Boolean): RemoteLoggerBuilder {
        this.isCrashLogsEnabled = isCrashLogsEnabled
        return this
    }

    fun build() {
        isValidateRemoteLogger()
        RemoteLogger(this)
    }

    private fun isValidateRemoteLogger(): Boolean {
        return !uploadUrl.isNullOrBlank() && application != null
    }
}