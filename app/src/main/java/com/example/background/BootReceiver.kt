package com.example.background

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.background.workers.BlurWorker

/**
 * StartReceiver.kt
 * android-workmanager
 * Created by 田村 柾優紀 on 2020/04/09.
 * Copyright © 2020 田村 柾優紀. All rights reserved.
 */
class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            invokeWorkManagerProcess(it)
//            invokeStartUpSelectImageActivity(it)
        }
    }

    private fun invokeWorkManagerProcess(context: Context) {
        val workManager = WorkManager.getInstance(context)
        workManager.enqueue(OneTimeWorkRequest.from(BlurWorker::class.java))
    }

    private fun invokeStartUpSelectImageActivity(context: Context) {
        val intent = Intent(context, SelectImageActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(intent)
    }
}