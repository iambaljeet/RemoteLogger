package com.app.logger

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.lib.remotelogger.fileutility.FileUtility
import com.lib.remotelogger.utility.RemoteLogger
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_make_crash?.setOnClickListener(this)
        button_log?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.button_make_crash -> {
                val remoteLogger: RemoteLogger? = null
                remoteLogger!!.toString()
            }
            R.id.button_log -> {
                for (i in 0..50) {
                    RemoteLogger.e(TAG = TAG, message = "Hello user $i")
                }
                val createFile = FileUtility(application = application)
                    .createFile()

                Log.e(TAG, "LogFile: ${createFile.readText()}")
            }
        }
    }
}