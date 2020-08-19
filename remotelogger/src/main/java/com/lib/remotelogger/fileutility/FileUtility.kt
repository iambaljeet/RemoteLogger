package com.lib.remotelogger.fileutility

import android.app.Application
import android.content.Context
import androidx.core.content.ContextCompat
import java.io.File

class FileUtility(application: Application?) {

    private var context: Context? = application?.applicationContext

    private fun getFilesDirectory(): File? {
        val directory = context?.let { ContextCompat.getDataDir(it) }
        return directory
    }

    private fun getLogsDirectory(): File {
        val filesDirectory = getFilesDirectory()
        val logsDirectory = File(filesDirectory, "Logs")
        logsDirectory.mkdir()
        return logsDirectory
    }

    private fun getFileName(logFileNamePrefix: String?): String {
        val fileName =if (!logFileNamePrefix.isNullOrBlank()) {
            "${logFileNamePrefix}_${context?.packageName}_logs.txt"
        } else {
            "${context?.packageName}_logs.txt"
        }
        return fileName
    }

    fun createFile(logFileNamePrefix: String?): File {
        val logsDirectory = getLogsDirectory()
        val fileName = getFileName(logFileNamePrefix)
        val logFile = File(logsDirectory, fileName)
        if (!logFile.exists()) {
            logFile.createNewFile()
        }

        return logFile
    }
}