package com.lib.remotelogger.crashutility

import java.io.PrintWriter
import java.io.StringWriter
import java.io.Writer

class CrashReporterExceptionHandler(private val getCrash: (String) -> Unit): Thread.UncaughtExceptionHandler {
    private val exceptionHandler: Thread.UncaughtExceptionHandler? = Thread.getDefaultUncaughtExceptionHandler()
    override fun uncaughtException(
        thread: Thread,
        throwable: Throwable
    ) {
        val stackTrace = getStackTrace(throwable).toString()
        getCrash(stackTrace)
        exceptionHandler?.uncaughtException(thread, throwable)
    }

    private fun getStackTrace(e: Throwable): String? {
        val result: Writer = StringWriter()
        val printWriter = PrintWriter(result)
        e.printStackTrace(printWriter)
        val crashLog: String = result.toString()
        printWriter.close()
        return crashLog
    }
}