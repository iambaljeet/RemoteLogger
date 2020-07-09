package com.lib.remotelogger.utility

import android.app.Application
import android.util.Log
import com.lib.remotelogger.fileutility.FileUtility
import com.lib.remotelogger.logutility.LogFormatter
import com.lib.remotelogger.logutility.RemoteLoggerBuilder
import java.io.File
import java.io.PrintWriter
import java.io.StringWriter
import java.io.Writer

class RemoteLogger(builder: RemoteLoggerBuilder) {
    private var application: Application? = builder.getApplication()
    private var uploadUrl: String? = builder.getUploadUrl()
    private var isRemoteLoggingEnabled: Boolean = builder.getIsRemoteLoggingEnabled()
    private var uploadLogOnLaunch: Boolean = builder.getUploadLogOnLaunch()
    private var isCrashReportingEnabled: Boolean = builder.getIsCrashLogsEnabled()

    val exceptionHandler = Thread.setDefaultUncaughtExceptionHandler { thread, e ->
        val result: Writer = StringWriter()
        val printWriter = PrintWriter(result)
        e.printStackTrace(printWriter)
        val stacktrace: String = result.toString()

        recordCrashLog(thread = thread, message = stacktrace)

        printWriter.close()
    }

    init {
        logFile = FileUtility(application = application).createFile()
        fileToWriteLog = FileUtility(application = application).createFile()
        logFormatter = LogFormatter(application = application)
    }

    companion object {
        private var logFile: File? = null
        private var logFormatter: LogFormatter? = null
        private var fileToWriteLog: File? = null

        private fun recordCrashLog(
            thread: Thread,
            message: String
        ) {
            val formattedLog =
                logFormatter?.formatCrashLog(thread = thread, message = message)

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