package com.lib.remotelogger.utility

import android.app.Application
import android.util.Log
import androidx.annotation.WorkerThread
import com.lib.remotelogger.crashutility.CrashReporterExceptionHandler
import com.lib.remotelogger.fileutility.FileUtility
import com.lib.remotelogger.logutility.LogFormatter
import com.lib.remotelogger.logutility.RemoteLoggerBuilder
import java.io.File

class RemoteLogger(builder: RemoteLoggerBuilder) {
    private var application: Application? = builder.getApplication()
    private var logFileNamePrefix: String? = builder.getLogFileNamePrefix()
    private var crashLogsEnabled: Boolean = builder.getCrashLogsEnabled()

    init {
        fileToWriteLog = FileUtility(application = application).createFile(logFileNamePrefix)
        logFormatter = LogFormatter(application = application)
        if (crashLogsEnabled) {
            setupCrashReporting()
        }
    }

    companion object {
        private var logFormatter: LogFormatter? = null
        private var fileToWriteLog: File? = null

        private fun setupCrashReporting() {
            val crashReporterExceptionHandler = CrashReporterExceptionHandler() { stackTrace ->
                recordCrashLog(crashDetails = stackTrace)
            }
            Thread.setDefaultUncaughtExceptionHandler(crashReporterExceptionHandler)
        }

        private fun recordCrashLog(
            crashDetails: String
        ) {
            val formattedLog =
                logFormatter?.formatCrashLog(message = crashDetails)

            formattedLog?.let { fileToWriteLog?.appendText(it) }
        }

        private fun recordLog(
            TAG: String,
            message: String,
            throwable: Throwable? = null,
            logLevel: Int
        ) {
            val formattedLog =
                logFormatter?.formatLog(tag = TAG, message = message, throwable = throwable, logLevel = logLevel)

            formattedLog?.let { fileToWriteLog?.appendText(it) }
        }

        @JvmStatic
        fun getLogFile(): File? {
            return fileToWriteLog
        }

        @JvmStatic
        @WorkerThread
        fun readLogs(): String? {
            return fileToWriteLog?.readText()
        }

        @JvmStatic
        @JvmOverloads
        fun e(TAG: String, message: String? = null, throwable: Throwable? = null) {
            Log.e(TAG, message, throwable)
            recordLog(
                TAG = TAG,
                message = message ?: String(),
                throwable = throwable,
                logLevel = Log.ERROR
            )
        }

        @JvmStatic
        @JvmOverloads
        fun v(TAG: String, message: String? = null, throwable: Throwable? = null) {
            Log.v(TAG, message, throwable)
            recordLog(
                TAG = TAG,
                message = message ?: String(),
                throwable = throwable,
                logLevel = Log.VERBOSE
            )
        }

        @JvmStatic
        @JvmOverloads
        fun d(TAG: String, message: String? = null, throwable: Throwable? = null) {
            Log.d(TAG, message, throwable)
            recordLog(
                TAG = TAG,
                message = message ?: String(),
                throwable = throwable,
                logLevel = Log.DEBUG
            )
        }

        @JvmStatic
        @JvmOverloads
        fun i(TAG: String, message: String? = null, throwable: Throwable? = null) {
            Log.i(TAG, message, throwable)
            recordLog(
                TAG = TAG,
                message = message ?: String(),
                throwable = throwable,
                logLevel = Log.INFO
            )
        }
    }
}