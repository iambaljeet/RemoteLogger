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

    private fun getFileName(): String {
        val fileName = "${context?.packageName}_logs.txt"
        return fileName
    }

    fun createFile(): File {
        val logsDirectory = getLogsDirectory()
        val fileName = getFileName()
        val logFile = File(logsDirectory, fileName)

        return logFile
    }
}